package com.berktas.rentacar.core.websocket;



import com.berktas.rentacar.business.abstracts.AuthorizationToViewVehicleInformationService;
import com.berktas.rentacar.business.concretes.CarManager;
import com.berktas.rentacar.business.concretes.UserDetailsManager;
import com.berktas.rentacar.core.security.CustomUserDetails;
import com.berktas.rentacar.core.security.JwtToken;
import com.berktas.rentacar.core.security.JwtTokenUtil;
import com.berktas.rentacar.model.entity.Car;
import com.berktas.rentacar.model.enums.Role;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class ChannelInterceptorImpl implements ChannelInterceptor {

    public static final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenUtil jwtTokenUtil;
    private final CarManager carManager;
    private final UserDetailsManager userDetailsManager;
    private final AuthorizationToViewVehicleInformationService authorizationToViewVehicleInformationService;

    public ChannelInterceptorImpl(JwtTokenUtil jwtTokenUtil,
                                  CarManager vehicleManager,
                                  UserDetailsManager userDetailsManager,
                                  AuthorizationToViewVehicleInformationService authorizationToViewVehicleInformationService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.carManager = vehicleManager;
        this.userDetailsManager = userDetailsManager;
        this.authorizationToViewVehicleInformationService = authorizationToViewVehicleInformationService;
    }


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel messageChannel) {
        //StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.SEND == accessor.getCommand()) {

            //
        }

        if (StompCommand.CONNECT == accessor.getCommand()) {
            //
        } else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
            validateSubscription(accessor);
        }



        return message;
    }


    private void validateSubscription(StompHeaderAccessor accessor) {

        //check header
        String token = accessor.getNativeHeader("token").get(0);

        //token prefix control
        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        //extract token
        String authToken = token.replace(TOKEN_PREFIX, "");
        JwtToken jwtToken = JwtToken.parse(authToken);

        //load userdetails
        String username = jwtToken.getClaims().get(JwtTokenUtil.KEY_USERNAME, String.class);
        CustomUserDetails userDetails = userDetailsManager.loadUserByUsername(username);

        //userdetails not found
        if (userDetails == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            //TODO throw new MessagingException("ERROR");
        }

        //check validity
        if (!jwtTokenUtil.isValid(jwtToken, userDetails)) {
            //throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            //TODO throw new MessagingException("ERROR");
        }

        //check topic

        //if topic vehicle
        String destination = accessor.getDestination();
        if (MqttTopic.isMatched("vehicle/+", destination)) {
            String c = destination.replace("vehicle/", "").split("/")[0];
            Long vehicleId = Long.parseLong(c);

            Car vehicle = carManager.findById(vehicleId);

            boolean result = false;

            if (userDetails.getUser().getRole() == Role.ROLE_ADMIN) {
                result = true;
            }

            if (!result) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                //TODO throw new MessagingException("ERROR");
            }
        }
    }


}
