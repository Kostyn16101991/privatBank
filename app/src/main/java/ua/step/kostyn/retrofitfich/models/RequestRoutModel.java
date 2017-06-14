package ua.step.kostyn.retrofitfich.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by konstantin on 03.06.17.
 */

public class RequestRoutModel {
    private String status;
    private List<Routes> routes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    public class Routes {
        private String summary;
        private List<Legs> legs;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<Legs> getLegs() {
            return legs;
        }

        public void setLegs(List<Legs> legs) {
            this.legs = legs;
        }

        public class Legs {
            private List<Steps> steps;

            public List<Steps> getSteps() {
                return steps;
            }

            public void setSteps(List<Steps> steps) {
                this.steps = steps;
            }

            public class Steps {
                @SerializedName("start_location")
                private MyLocation startLocation;
                @SerializedName("end_location")
                private MyLocation endLocation;

                public MyLocation getStartLocation() {
                    return startLocation;
                }

                public void setStartLocation(MyLocation startLocation) {
                    this.startLocation = startLocation;
                }

                public MyLocation getEndLocation() {
                    return endLocation;
                }

                public void setEndLocation(MyLocation endLocation) {
                    this.endLocation = endLocation;
                }

                @Override
                public String toString() {
                    return "Steps{" +
                            "startLocation=" + startLocation +
                            ", endLocation=" + endLocation +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Legs{" +
                        "steps=" + steps +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Routes{" +
                    "summary='" + summary + '\'' +
                    ", legs=" + legs +
                    '}';
        }
    }

    public class MyLocation {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "MyLocation{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RequestRoutModel{" +
                "status='" + status + '\'' +
                ", routesSSS=" + routes +
                '}';
    }
}
