package com.berktas.rentacar.controller.map;


import com.berktas.rentacar.core.util.annotation.PermitAllCustom;
import com.berktas.rentacar.service.osrm.LngLat;
import com.berktas.rentacar.service.osrm.OsrmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("route")
@PermitAllCustom
@RequiredArgsConstructor
public class RouteController {

    private final OsrmService osrmService;

    @PostMapping("/optimizedRoute")
    public List<LngLat> getOptimizedRoute(@RequestBody List<LngLat> station) throws IOException {
     return osrmService.optimizedRoute(station);
    }

}
