

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
        const pantries = [
            ["St. Mark's Lutheran Church Pantry", 43.052715, -89.39253, 4],
            ["St. Stephen's Lutheran Church Pantry", 43.057793, -89.33015, 5],
            ["St. Vincent de Paul Food Pantry	", 3.04317, -89.40315, 3],
            ["Wil-Mar Neighborhood Center Food Pantry", 43.07976, -89.36697, 2],
            ["Allied Food Pantry", 43.028458, -89.45776, 1],
        ];
        // Initialize and add the map
        function initMap() {
            let options = {//mao options
                zoom: 12,
                center: {lat: 43.0731, lng: -89.4012}
            }
            // The location of Madison
            //  const madison = {lat: 43.0731, lng: -89.4012};
            // The map, centered at Madison
            const map = new google.maps.Map(document.getElementById("map"), options);

            setMarkers(map);
            // Data for the markers consisting of a name, a LatLng and a zIndex for the
            // order in which these markers should display on top of each other.


            function setMarkers(map) {
                // Adds markers to the map.
                // Marker sizes are expressed as a Size of X,Y where the origin of the image
                // (0,0) is located in the top left of the image.
                // Origins, anchor positions and coordinates of the marker increase in the X
                // direction to the right and in the Y direction down.
                const image = {
                    url: "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png",
                    // This marker is 20 pixels wide by 32 pixels high.
                    size: new google.maps.Size(20, 32),
                    // The origin for this image is (0, 0).
                    origin: new google.maps.Point(0, 0),
                    // The anchor for this image is the base of the flagpole at (0, 32).
                    anchor: new google.maps.Point(0, 32),
                };
                // Shapes define the clickable region of the icon. The type defines an HTML
                // <area> element 'poly' which traces out a polygon as a series of X,Y points.
                // The final coordinate closes the poly by connecting to the first coordinate.
                const shape = {
                    coords: [1, 1, 1, 20, 18, 20, 18, 1],
                    type: "poly",
                };

                for (let i = 0; i < pantries.length; i++) {
                    const pantry = pantries[i];
                    new google.maps.Marker({
                        position: {lat: pantry[1], lng: pantry[2]},
                        map,
                        icon: image,
                        shape: shape,
                        title: pantry[0],
                        label: pantry[0],
                        zIndex: pantry[3],
                    });
                }
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
        async
></script>
</body>
</html>
