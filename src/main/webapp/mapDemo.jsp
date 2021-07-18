

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
        function initMap() {
            // The location of Madison
            const madison = { lat: 43.0731, lng:  -89.4012};
            // The map, centered at Madison
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 9,
                center: madison,
            });
            // The marker, positioned at Madison
            const marker = new google.maps.Marker({
                position: madison,
                map: map,
            });

            // NOTE: This uses cross-domain XHR, and may not work on older browsers.
            map.data.loadGeoJson(
                "https://storage.googleapis.com/mapsdevsite/json/google.json"
            );
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
