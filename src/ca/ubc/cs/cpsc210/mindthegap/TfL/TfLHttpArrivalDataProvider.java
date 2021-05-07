package ca.ubc.cs.cpsc210.mindthegap.TfL;

/*
 * Copyright 2015-2016 Department of Computer Science UBC
 */

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;
import ca.ubc.cs.cpsc210.mindthegap.model.StationManager;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    //private static final String ARRIVALS_API_BASE = "https://api.tfl.gov.uk";              // for TfL data
    private static final String ARRIVALS_API_BASE = "http://kunghit.ugrad.cs.ubc.ca:6060";   // for simulated data (3pm to midnight)
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    protected URL getURL() throws MalformedURLException {
        String lineIDs = "";
        String stopPointID = StationManager.getInstance().getSelected().getID();
        int stopValue = 1;
        if (!(StationManager.getInstance().getSelected() == null)) {
            for (Line line : StationManager.getInstance().getSelected().getLines()) {
                if (!(StationManager.getInstance().getSelected().getLines().size() == stopValue)) {
                    lineIDs = lineIDs + line.getId() + ",";
                    stopValue++;
                } else lineIDs = lineIDs + line.getId();
            }
        }
        String urlValue = ARRIVALS_API_BASE + "/Line/" + lineIDs + "/Arrivals?stopPointId=" + stopPointID + "&app_id=&app_key=";

        return new URL(urlValue);
    }
}
