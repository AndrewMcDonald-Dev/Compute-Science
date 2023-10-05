<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]>      <html class="no-js"> <!--<![endif]-->
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>My Google Map</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="">
        <style>
            #map{
                height:600px;
                width:50%;
            }

        </style>
    </head>
    <body>
        <div id="map"></div>
            <?php   
            // Class useful for storing data so that it is easy to read in js
            class Location {
                public $title;
                public $lat;
                public $lng;
                public $landmark;

                function __construct(String $title, String $lat, String $lng, String $landmark) {
                    $this->title = $title;
                    $this->lat = $lat;
                    $this->lng = $lng;
                    $this->landmark = $landmark;
                }
            }
            if($item->landmark == NULL) {
                $r_vals = new Location($item->title, $item->lat, $item->lng, '0');
            }else {
                $r_vals = new Location($item->title, $item->lat, $item->lng, $item->landmark);
            }
            ?>


        <script>
            function initMap(){
                // Temp variables that grab the php for use in js
                const location = <?php  echo json_encode($r_vals); ?>;
                const data = <?php echo $item->data; ?>;
                 
                const marker = {
                    coords: {
                        lat:Number(location.lat), lng:Number(location.lng)
                    },
                    title: location.title,
                    data,
                    landmark: location.landmark
                };

                //map options
                var options = {
                    zoom:13,
                    center:marker.coords,
                    fullscreenControl: true,
				styles: [
					{
						featureType: "poi.business",
						stylers: [{ visibility: "off"}]
					},
					{
						featureType: "transit",
		  				elementType: "labels.icon",
						stylers: [{ visibility: "off" }],
					}
				]
                }
                //New Map Created
                var map = new google.maps.Map(document.getElementById('map'), options);

				const build_content_popup = (title,data) => {
					let content =`<table><tr><td>Address</td><td>${title}</td></tr>`;

					if (data["Date of Construction"])
						content +=`<tr><td>Constructed in:</td><td>${data["Date of Construction"]}</td></tr>`

					if (data["Historic Use"])
						content += `<tr><td>Historic Use:</td><td>${data["Historic Use"]}</td></tr>`

					if (data["Present Use"])
						content += `<tr><td>Present Use:</td><td>${data["Present Use"]}</td></tr>`

					content += `</table>`
					return content
				}

                const mapInfoToMarker = ({title, data, coords, landmark}) => {
                    let url = "";

                    if (landmark == 1) {
                        url = "/p/s23-06/images/pins/yellowpin.png"
                    } else if (data["Date of Construction"] && Number(data["Date of Construction"].split("c.").pop()) > 1930) {
                        url = "/p/s23-06/images/pins/graypin.png"
                    } else if (data["Construction method"] && data["Construction method"].includes("wood frame")) {
                        url = "/p/s23-06/images/pins/woodpin.png"
                    } else if (data["Construction method"] && data["Construction method"].includes("brick")) {
                        url = "/p/s23-06/images/pins/brickredpin.png"
                    } else if (data["Construction method"] && data["Construction method"].includes("stone")) {
                        url = "/p/s23-06/images/pins/darkgreenpin.png"
                    } else {
                        url = "/p/s23-06/images/pins/whitepin.png"
                    }

                    let testMarker = new google.maps.Marker({
                        position: coords,
                        icon: {
                            url: url
                        },
                        map
                    })

                    testMarker.addListener("click", () => {
                        infoWindow.close()
                        infoWindow = new google.maps.InfoWindow({
                            content: build_content_popup(title, data, id, landmark)
                        })

                        infoWindow.open({
                            anchor: testMarker,
                            map
                        })
                    })
                }
                //Draws the marker
                mapInfoToMarker(marker);
            }
            
            </script>
        <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtUZ8-rcfv4hhFXSdPE29liR0_fZloskw&callback=initMap&v=weekly"
      defer
    ></script>
        <script src="" async defer></script>
    </body>
</html>