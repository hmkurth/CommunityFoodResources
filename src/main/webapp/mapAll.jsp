
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Map</title>

    <style type="text/css">
        /* Set the size of the div element that contains the map */
        #map {
            height: 400px;
            /* The height is 400 pixels */
            width: 100%;
            /* The width is the width of the web page */
        }
    </style>
    <script>

        // Initialize and add the map
        let map;
        let marker;

        let options;
        function initMap() {

                //loop through java list to extract variables and reassign
        const allResources = [

            <c:forEach items="${allMapped}" var="item">
                    {
                     type: "${item.typeId.name}",
                      name: "${item.name}",
                      id: "${item.id}",
                     lat:${item.location.lat},
                     lng: ${item.location.lng},
                } ,
            </c:forEach>
        ];

        for(let i = 0; i < allResources.length; i++) {
            console.log(allResources[i].name);
        }

                const contentString =
                    '<div id="content">' +
                    '<div id="siteNotice">' +
                    '</div>';

                let options = {//mao options
                    zoom: 12,
                    center: {lat: 43.0731, lng: -89.4012}
                }
                const map = new google.maps.Map(document.getElementById("map"), options);
                setMarkers(map);


                //accessible markers
                // Create an info window to share between markers.
                const infoWindow = new google.maps.InfoWindow({
                    content: contentString,
                })

                // Create the markers.
                function setMarkers(map) {
                    for (let i = 0; i < allResources.length; i++) {
                        const pantry = allResources[i];
                        marker = new google.maps.Marker({
                            position: {lat: parseFloat(pantry[3]), lng: parseFloat(pantry[4])},
                            map,
                            title: pantry[1],
                            label: pantry[1],
                            optimized: false,
                        });

                        marker.addListener("click", () => {
                            infoWindow.close();
                            console.log("in add listener")
                            infoWindow.setContent(marker.getTitle());
                            infoWindow.open(marker.getMap(), marker);
                        })//info window
                    }//foreach

                    console.log("allResources: " + allResources.toString());
                }

        }
    </script>

</head>
<body>
<h3>My Google Maps Demo</h3>
<!--The div element for the map -->
<div id="map"></div>
<!-- Async script executes immediately and must be after any DOM elements used in callback. -->
<script

        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCLGoKo1ZhK7TyAsvpPwQZmlLsAQxWnpRM&callback=initMap&libraries=&v=weekly"
        async defer
></script>

</body>
</html>
