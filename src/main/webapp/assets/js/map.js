// [START maps_map_simple]
let map;
// Initialize and add the map
function initMap() {
    // The location of Madison
    const madison = { lat: 43.0731, lng:  -89.4012};
    // The map, centered at Madison
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 12 ,
        center: madison,
    });
    // The marker, positioned at Madison
    const marker = new google.maps.Marker({
        position: madison,
        map: map,
    });
}

// [END maps_map_simple]
