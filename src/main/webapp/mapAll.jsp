
<%@include file="/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Map of Madison Food Resources</title>
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
        let allResourcesJS;

        let options;
        function initMap() {
console.log("initMap start");
      //     let resource1= JSON.parse('${allMapped[0]}');  //convert the string to a JSON object, causes syntax error, missing ')' after argument list
         //   console.log('resource1: ' + resource1);//this returns all the info from the toString , too much
                //loop through java list to extract variables and reassign, or use JS map function

            //console.log("allMapped list from java: " +  ${allMapped});
            allResourcesJS = [

            <c:forEach items="${allMapped}" var="item">
                    {
                     idType: "${item.type}",
                      name: "${item.name}",
                      id: "${item.id}",
                     lat:${item.location.lat},
                     lng: ${item.location.lng},

                } ,
                console.log("foreach: " +  allResourcesJS.idType)//this is not running
            </c:forEach>
        ];


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
                    console.log("settingMarkers:, allresources.length: "+  allResourcesJS.length)
                    for (let i = 0; i < allResourcesJS.length; i++) {
                        const pantry = allResourcesJS[i];
                        marker = new google.maps.Marker({
                            position: {lat: parseFloat(pantry[3]), lng: parseFloat(pantry[4])},
                            map,
                            title: pantry[1],
                            label: pantry[1],
                            optimized: false,
                        });

                        marker.addListener("click", () => {
                            infoWindow.close();
                            console.log("in add listener")//not running
                            infoWindow.setContent(marker.getTitle());
                            infoWindow.open(marker.getMap(), marker);
                        })//info window
                    }//foreach
/*
                    console.log("allResourcesJS: " + allResourcesJS.toString());//runs, but blank
                    for (let i = 0; i < allResourcesJS.length; i++) {
                        console.log(allResourcesJS[i].name);//doesn't run
                    }
                    */

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
