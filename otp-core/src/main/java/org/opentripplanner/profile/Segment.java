package org.opentripplanner.profile;

import java.util.Set;

import lombok.Getter;

import org.onebusaway.gtfs.model.Stop;
import org.opentripplanner.profile.ProfileData.Pattern;
import org.opentripplanner.profile.ProfileRouter.Ride;

import com.beust.jcommander.internal.Sets;

public class Segment {

    @Getter String route;
    @Getter String from;
    @Getter String to;
    @Getter String qualifier;
    @Getter Set<String> stops = Sets.newHashSet();
    
    public Segment (Ride ride) {
        route = ride.route.getId().getId();
        from = ride.from.getId().getId();
        to = ride.to.getId().getId();
        for (Pattern pattern : ride.patterns) {
            boolean onboard = false;
            for (Stop stop : pattern.stops) {
                if (!onboard) {
                    if (stop == ride.from) onboard = true;
                    else continue;
                }
                stops.add(stop.getId().getId());
                if (stop == ride.to) break;
            }
        }
    }
    
}
