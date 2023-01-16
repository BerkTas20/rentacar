package com.berktas.rentacar.controller.map;


import com.berktas.rentacar.core.util.annotation.PermitAllCustom;
import com.berktas.rentacar.service.nominatim.NominatimService;
import com.berktas.rentacar.service.osrm.LngLat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("map")
@PermitAllCustom
@RequiredArgsConstructor
public class MapController {

    private final NominatimService nominatimService;

    @Operation(
            summary = "Search On The Map",
            description = " This request shows the borderlines of the district"



    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved",content = @Content),
                    @ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Access to the relevant resource is prohibited",content = @Content),
            }
    )
    @PostMapping("/search")
    public List<LngLat> search(@RequestBody MapSearchRequest mapSearchRequest) throws IOException {
       return nominatimService.search(mapSearchRequest);
    }


}
