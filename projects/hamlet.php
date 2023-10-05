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
	<link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,100italic,300italic,400italic,500italic,500,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
	<style>
		#map {
			height: 98.2vh;
			width: 100%;
		}
		#legend {	
			font-family: 'Roboto';
			background: #fff;
			padding: 10px;
			margin: 10px;
			border: 3px solid #000;
			font-size: 14pt;
		}

		#legend h3 {
			margin-top: 0;
			text-align: center;
		}

		#legend img {
			vertical-align: middle;
		}
		td {
			font-weight: bold;
			color: #404040;
		}
		* {
			font-family: 'Roboto';
			font-weight: 700;
		}
	</style>
</head>

<body>
	<div id="map"></div>
	<div id="legend"><h2 style="margin-top: 0px; margin-bottom: 20px; text-align: center;">Legend</h2></div>
	<?php
	if (isset($_GET["hamlet"])){
	$hamlet = $_GET["hamlet"];
	require 'config.php';
	$query = "SELECT cid,title,lat,lng,`data`,landmark FROM a_all_items WHERE hamlet=" . $hamlet . ";";
	$items = mysqli_query($link, $query);
	?>

	<?php

	class Location {
		public $title;
		public $lat;
		public $lng;
		public $cid;
		public $landmark;

		function __construct(String $title, String $lat, String $lng, String $cid, String $landmark) {
			$this->title = $title;
			$this->lat = $lat;
			$this->lng = $lng;
			$this->cid = $cid;
			$this->landmark = $landmark;
		}
	}

	$r_vals = [];
	$data_vals = [];
	while ($vals = mysqli_fetch_object($items)) {
		if ($vals->landmark == NULL) {
			$vals->landmark = 0;
		}
		array_push($data_vals, $vals->data);
		array_push($r_vals, new Location($vals->title, $vals->lat, $vals->lng, $vals->cid, $vals->landmark));
	}
	?>


	<script>
		function initMap() {
			let items = 
			[
				{
					coords: {
						lat: 41.738,
						lng: -74.0838
					},
					type: "New Paltz",
					url: "newpaltz"
				},
				{
					coords: {
						lat: 41.8026,
						lng: -74.0548
					},
					type: "Bonticou",
					url: "bonticou"
				},
				{
					coords: {
						lat: 41.7606,
						lng: -74.1278
					},
					type: "Butterville",
					url: "butterville"
				},
				{
					coords: {
						lat: 41.7297,
						lng: -74.13121
					},
					type: "Libertyville",
					url: "libertyville"
				},
				{
					coords: {
						lat: 41.78,
						lng: -74.0608
					},
					type: "Middletown",
					url: "middletown"
				},
				{
					coords: {
						lat: 41.744,
						lng: -74.0608
					},
					type: "Ohioville",
					url: "ohioville"
				},
				{
					coords: {
						lat: 41.78,
						lng: -74.0358
					},
					type: "Plutarch",
					url: "plutarch"
				},
				{
					coords: {
						lat: 41.790,
						lng: -74.105
					},
					type: "Springtown",
					url: "springtown"
				},
				{
					coords: {
						lat: 41.748,
						lng: -74.0858
					},
					type: "Downtown",
					url: "downtown"
				},
				{
					coords: {
						lat: 41.7157,
						lng: -74.09121
					},
					type: "Jenkinstown",
					url: "jenkinstown"
				},
			]

			const hamlet = <?php echo $hamlet ?>
			//map options
			var options = {
				zoom: 14.5,
				center: items[hamlet-1].coords,
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

			var temp = <?php echo json_encode($r_vals); ?>;
			var temp2 = [
				<?php
				foreach ($data_vals as $data) {
					echo $data;
				?>,

				<?php
				}
				?>
			];

			var marker = [];
			temp.forEach((c, i) => {
				marker.push({
					coords: {
						lat: Number(c.lat),
						lng: Number(c.lng)
					},
					title: c.title,
					data: temp2[i],
					cid: c.cid,
					landmark: Number(c.landmark)
				})
			})

			if (hamlet == 2){
				var bonti = [ {lat:41.8088487836534,lng:-74.0681299516366}, {lat:41.8054308244545,lng:-74.0676842849692},{lat:41.8051447380514,lng:-74.0676103403529},{lat:41.8039128552856,lng:-74.0671819386039},{lat:41.80320474605,lng:-74.0671920552033},{lat:41.8028974559558,lng:-74.0673618725652},{lat:41.7976644651652,lng:-74.0454253607363},{lat:41.8006103209448,lng:-74.0450231628007},{lat:41.8006122627667,lng:-74.0450229350875},{lat:41.8088487836534,lng:-74.0681299516366}    ];
				var Bonticou= new google.maps.Polygon({
					paths: bonti,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#92BDA3',
					fillOpacity: .4
				});
				Bonticou.setMap(map);
			} else if (hamlet == 3) {
				var butter = [{ lat:41.7872484357637, lng: -74.1275920039124}, { lat:41.7797418700764, lng: -74.1357656137944}, { lat:41.7623846094761, lng: -74.1547256994048}, { lat:41.7599620496269, lng: -74.1573505249058}, { lat:41.7537604160904, lng: -74.16416442577}, { lat:41.7536367866716, lng: -74.1643002426313}, { lat:41.7535605995373, lng: -74.1643839399745}, { lat:41.7535338927275, lng: -74.1644132793665}, { lat:41.7510970946609, lng: -74.1387194329634}, { lat:41.7415111079964, lng: -74.1232415463839}, { lat:41.7404182720897, lng: -74.1155493906126}, { lat:41.7490943713559, lng: -74.1089135059911}, { lat:41.7497284261984, lng: -74.1054169724866}, { lat:41.750681870227, lng: -74.1001579090183}, { lat:41.7513737342147, lng: -74.0963407518379}, { lat:41.7519309762072, lng: -74.0932657615784}, { lat:41.7532748545364, lng: -74.0948274884027}, { lat:41.7541717982109, lng: -74.0960003831667}, { lat:41.7552135595859, lng: -74.0967761085014}, { lat:41.7566929109161, lng: -74.0967553653288}, { lat:41.7575680841929, lng: -74.0951623462085}, { lat:41.7588901565661, lng: -74.0939581893304}, { lat:41.7603648296173, lng: -74.093344638767}, { lat:41.7618379382322, lng: -74.0925334751069}, { lat:41.7631693513455, lng: -74.0925147142791}, { lat:41.7652404377814, lng: -74.0924855282569}, { lat:41.766423915401, lng: -74.0924688491566}, { lat:41.7680529770465, lng: -74.092445627064}, { lat:41.769385729848, lng: -74.092822362254}, { lat:41.7705645237796, lng: -74.0922128532226}, { lat:41.7711549186252, lng: -74.0918089969843}, { lat:41.7720376202754, lng: -74.0914015311649}, { lat:41.7727759464169, lng: -74.0909955692637}, { lat:41.7736602089305, lng: -74.0907857023111}, { lat:41.7749284526245, lng: -74.0898711300187}, { lat:41.7872484357637, lng: -74.1275920039124}];
				var Butterville = new google.maps.Polygon({
					paths: butter,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#A1BA89',
					fillOpacity: .4
				});
				Butterville.setMap(map);
			} else if (hamlet == 4){
				var liberty = [{ lat: 41.7491059650515, lng:-74.1690580743632}, { lat: 41.7438618661491, lng:-74.1607115152247}, { lat: 41.7405027258653, lng:-74.1554281103194}, { lat: 41.7364078164178, lng:-74.1489887113628}, { lat: 41.7327907618598, lng:-74.1433019047456}, { lat: 41.7288300601075, lng:-74.1370760431327}, { lat: 41.7254209321738, lng:-74.1317182378077}, { lat: 41.7216439233307, lng:-74.1257833836644}, { lat: 41.71832335979, lng:-74.1211123722233}, { lat: 41.7146562385569, lng:-74.1161199031092}, { lat: 41.7116884182018, lng:-74.1120451154979}, { lat: 41.7115850092509, lng:-74.1119000142978}, { lat: 41.7110565173597, lng:-74.1111584571464}, { lat: 41.7093334192767, lng:-74.1087408102101}, { lat: 41.7084909000149, lng:-74.1073313044814}, { lat: 41.7114922596215, lng:-74.1058759632838}, { lat: 41.7126696048206, lng:-74.1050698231481}, { lat: 41.7135541482069, lng:-74.1046626357178}, { lat: 41.7151737534537, lng:-74.1036528398282}, { lat: 41.7163618642017, lng:-74.1042287320111}, { lat: 41.7173881776477, lng:-74.1030296581827}, { lat: 41.7231001212224, lng:-74.1146019973853}, { lat: 41.725442717215, lng:-74.1114099143985}, { lat: 41.7270593197586, lng:-74.1100051379244}, { lat: 41.7297022593103, lng:-74.107401053137}, { lat: 41.7311770173199, lng:-74.1067880985025}, { lat: 41.7339893392584, lng:-74.1069466546997}, { lat: 41.7369403422355, lng:-74.1064861349083}, { lat: 41.740417817551, lng:-74.1155493841794}, { lat: 41.7415124135436, lng:-74.1232385892738}, { lat: 41.7510984006031, lng:-74.1387164757169}, { lat: 41.7535338927275, lng:-74.1644132793665}, { lat: 41.7501261138779, lng:-74.1681566912241}, { lat: 41.7500129120344, lng:-74.1682567597826}, { lat: 41.7493021317301, lng:-74.1688850678765}, { lat: 41.7491059650515, lng:-74.1690580743632}];
				var Libertyville = new google.maps.Polygon({
					paths: liberty,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#A5CC6B',
					fillOpacity: .4
				});
				Libertyville.setMap(map);
			} else if (hamlet == 5) {
				var middle = [{ lat:41.7976644651652, lng:-74.0454253607363}, { lat:41.8028974551452, lng:-74.0673618725769}, { lat:41.8027174560261, lng:-74.0674506992185}, { lat:41.8020205846039, lng:-74.0681649481597}, { lat:41.801841209531, lng:-74.0683892847443}, { lat:41.8007847361983, lng:-74.0697759013361}, { lat:41.8010454127052, lng:-74.0707984344367}, { lat:41.8015874482819, lng:-74.0714852202935}, { lat:41.8019956873314, lng:-74.0717712454409}, { lat:41.803015207705, lng:-74.0721778043593}, { lat:41.8039771128283, lng:-74.0724305313145}, { lat:41.8040991875969, lng:-74.0724592189012}, { lat:41.8048044063227, lng:-74.0726185236894}, { lat:41.8055901814856, lng:-74.0730226693691}, { lat:41.8062762520329, lng:-74.0736758834211}, { lat:41.8068149536955, lng:-74.0744361262465}, { lat:41.8073399458429, lng:-74.075536658559}, { lat:41.8075802628436, lng:-74.0767366404803}, { lat:41.8076712696441, lng:-74.0778066528044}, { lat:41.8075204966749, lng:-74.0788507230021}, { lat:41.8073000137189, lng:-74.079813259611}, { lat:41.8069097566243, lng:-74.080840169538}, { lat:41.8064018912725, lng:-74.0817427775001}, { lat:41.8057196080652, lng:-74.0826742071487}, { lat:41.8048879133671, lng:-74.0836071110766}, { lat:41.8040071768912, lng:-74.0843444038236}, { lat:41.8034108082188, lng:-74.0846493763114}, { lat:41.8030995140323, lng:-74.0848046532383}, { lat:41.8022175356399, lng:-74.0850047363804}, { lat:41.8013966923289, lng:-74.0849475016802}, { lat:41.8010649223906, lng:-74.0848484481813}, { lat:41.8004192263533, lng:-74.0846507029311}, { lat:41.7990694435554, lng:-74.0846459174495}, { lat:41.7982315194011, lng:-74.0843021098849}, { lat:41.7975634873486, lng:-74.0838469610582}, { lat:41.7969790079471, lng:-74.0833381868229}, { lat:41.7958339939506, lng:-74.0824204907113}, { lat:41.7941389281184, lng:-74.0812233425399}, { lat:41.792961103074, lng:-74.0804299102062}, { lat:41.7917828669562, lng:-74.0803684551296}, { lat:41.7907405175771, lng:-74.0802639545057}, { lat:41.7900008497535, lng:-74.080274528256}, { lat:41.7891652747077, lng:-74.0796781947485}, { lat:41.7884703665665, lng:-74.0793428510655}, { lat:41.7879119490274, lng:-74.0791647833725}, { lat:41.7863550334824, lng:-74.0794963774511}, { lat:41.7856731813252, lng:-74.0800754484658}, { lat:41.7847674790689, lng:-74.0807842182545}, { lat:41.7839282095152, lng:-74.0812903919946}, { lat:41.7830484133624, lng:-74.0813861101724}, { lat:41.7821438366489, lng:-74.0808195560666}, { lat:41.7815375098239, lng:-74.080253913501}, { lat:41.7808164332712, lng:-74.0801453366511}, { lat:41.7798937289497, lng:-74.0805656905126}, { lat:41.7796615363064, lng:-74.0812842553206}, { lat:41.7794531544139, lng:-74.0819291168153}, { lat:41.7792477098383, lng:-74.0824838645913}, { lat:41.7788278244474, lng:-74.0834903064194}, { lat:41.7784846918368, lng:-74.0845558319408}, { lat:41.7780576601025, lng:-74.0853866088223}, { lat:41.7778407448696, lng:-74.0857106782605}, { lat:41.7775368807785, lng:-74.086164639604}, { lat:41.7769568324488, lng:-74.0874342992885}, { lat:41.7762328298281, lng:-74.0884761219077}, { lat:41.7754403591095, lng:-74.0892268203653}, { lat:41.7749302321313, lng:-74.0898708432262}, { lat:41.7736619884403, lng:-74.0907854155502}, { lat:41.7727759456063, lng:-74.0909955692752}, { lat:41.7720393997875, lng:-74.091401244429}, { lat:41.7711549178146, lng:-74.0918089969957}, { lat:41.7705663032945, lng:-74.0922125665163}, { lat:41.7700751335543, lng:-74.0924659006702}, { lat:41.769387509365, lng:-74.0928220755705}, { lat:41.7686774972725, lng:-74.0926225805184}, { lat:41.7680529762359, lng:-74.0924456270754}, { lat:41.7650031816229, lng:-74.0810268753613}, { lat:41.7604681210838, lng:-74.0690378568006}, { lat:41.7579644427174, lng:-74.0704572051033}, { lat:41.7549286280516, lng:-74.0610142634213}, { lat:41.7604310549664, lng:-74.059039074638}, { lat:41.769868950893, lng:-74.0554776993074}, { lat:41.7732490659121, lng:-74.0537151950622}, { lat:41.7738040914481, lng:-74.0534262064903}, { lat:41.7867698867495, lng:-74.0469123146103}, { lat:41.7976644651652, lng:-74.0454253607363}];
				var Middletown = new google.maps.Polygon({
					paths: middle,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#806D40',
					fillOpacity: .4
				});
				Middletown.setMap(map);
			} else if (hamlet == 6) {
				var ohio = [{ lat: 41.7738040922587, lng:-74.0534262064784}, { lat: 41.7698689297845, lng:-74.055475033268}, { lat: 41.7604310339445, lng:-74.0590364089866}, { lat: 41.7549286288622, lng:-74.0610142634095}, { lat: 41.7478996073579, lng:-74.0638071246992}, { lat: 41.7466774970494, lng:-74.064372768457}, { lat: 41.7455278897158, lng:-74.0649087810779}, { lat: 41.7447323878952, lng:-74.0652796772895}, { lat: 41.7418315266936, lng:-74.0672032526837}, { lat: 41.7422674033544, lng:-74.0687055158443}, { lat: 41.7297487757647, lng:-74.0757991359445}, { lat: 41.7271844800283, lng:-74.069713388547}, { lat: 41.7161466786058, lng:-74.076981373835}, { lat: 41.7139993647143, lng:-74.0714143560564}, { lat: 41.7148113981553, lng:-74.0707281451886}, { lat: 41.7160095662852, lng:-74.0696258337137}, { lat: 41.7171468519704, lng:-74.0684730807925}, { lat: 41.7182004413274, lng:-74.067174985854}, { lat: 41.718985415118, lng:-74.0665404118384}, { lat: 41.7198808540371, lng:-74.0659774838992}, { lat: 41.7214058545762, lng:-74.0652073266462}, { lat: 41.7203716789599, lng:-74.0631706333552}, { lat: 41.7200690528919, lng:-74.0624935627524}, { lat: 41.7214795825483, lng:-74.0600530749391}, { lat: 41.7224992644498, lng:-74.0580088372395}, { lat: 41.7248714639765, lng:-74.0554433346204}, { lat: 41.7258528247686, lng:-74.0544228548731}, { lat: 41.7270508683949, lng:-74.0531769991693}, { lat: 41.7281425434331, lng:-74.0519869434546}, { lat: 41.7291981664386, lng:-74.0508361368842}, { lat: 41.730547155245, lng:-74.0494599670645}, { lat: 41.7319193434066, lng:-74.0482226836264}, { lat: 41.7339784225057, lng:-74.0464619449573}, { lat: 41.735307457075, lng:-74.0441257867567}, { lat: 41.7368638867315, lng:-74.0435938719089}, { lat: 41.7382243782223, lng:-74.0431288970986}, { lat: 41.7397575296317, lng:-74.0426952664667}, { lat: 41.7409438932432, lng:-74.042164234952}, { lat: 41.7417624226673, lng:-74.0416314285982}, { lat: 41.7426301323236, lng:-74.0404821357564}, { lat: 41.7435314304541, lng:-74.0394056311231}, { lat: 41.7449256401919, lng:-74.0376179240705}, { lat: 41.7458483979549, lng:-74.0370834765875}, { lat: 41.7468342124617, lng:-74.0362475224778}, { lat: 41.748562485842, lng:-74.0349750786953}, { lat: 41.7494114514337, lng:-74.0341851355991}, { lat: 41.7508126645161, lng:-74.0331171299485}, { lat: 41.7519613794626, lng:-74.0326530039284}, { lat: 41.7525573495356, lng:-74.0323802921803}, { lat: 41.7540088794977, lng:-74.0320289164284}, { lat: 41.7552077295551, lng:-74.0316519455635}, { lat: 41.7562800505756, lng:-74.0312401997928}, { lat: 41.7581026207717, lng:-74.0306266942081}, { lat: 41.7591198087427, lng:-74.0301864030032}, { lat: 41.76441728991, lng:-74.0454620195608}, { lat: 41.7701685817176, lng:-74.0432028080963}, { lat: 41.7738040922587, lng:-74.0534262064784}];
				var Ohiotown = new google.maps.Polygon({
					paths: ohio,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#4F3130',
					fillOpacity: .4
				});
				Ohiotown.setMap(map);
			} else if (hamlet == 7) {
				var plut = [{ lat: 41.8006122627667, lng:-74.0450229350875}, { lat: 41.7867698867495, lng:-74.0469123146103}, { lat: 41.7738040923096, lng:-74.0534262126518}, { lat: 41.770168581769, lng:-74.0432028142694}, { lat: 41.7644172899612, lng:-74.0454620257333}, { lat: 41.7591198087948, lng:-74.0301864091752}, { lat: 41.760311884432, lng:-74.0296555335755}, { lat: 41.7622492095851, lng:-74.0289815949184}, { lat: 41.7634268374932, lng:-74.0280403635042}, { lat: 41.7649966519394, lng:-74.0273938089159}, { lat: 41.7655832606644, lng:-74.0273117306064}, { lat: 41.7666129508144, lng:-74.0264019466002}, { lat: 41.7686897977662, lng:-74.0253518257116}, { lat: 41.7693647787524, lng:-74.0253343921234}, { lat: 41.7705584606426, lng:-74.0249939390807}, { lat: 41.7713014357781, lng:-74.0245795587459}, { lat: 41.7719651981849, lng:-74.0245329488156}, { lat: 41.7726015218521, lng:-74.0238415516492}, { lat: 41.7734085958269, lng:-74.0238661058137}, { lat: 41.7749170456667, lng:-74.0237481554313}, { lat: 41.7762181964931, lng:-74.0237799664175}, { lat: 41.7769834393521, lng:-74.023401859708}, { lat: 41.777248857895, lng:-74.0216967786277}, { lat: 41.7783886496795, lng:-74.0208290693134}, { lat: 41.7786697986035, lng:-74.0209714844659}, { lat: 41.7792888520787, lng:-74.0208301734284}, { lat: 41.7812293321022, lng:-74.0205223080392}, { lat: 41.7818064200066, lng:-74.020608937459}, { lat: 41.7825016837477, lng:-74.0203931466499}, { lat: 41.7831309634381, lng:-74.0201636747219}, { lat: 41.7836237799655, lng:-74.020024243851}, { lat: 41.7846909734051, lng:-74.0190108411857}, { lat: 41.7853212518879, lng:-74.0188986649054}, { lat: 41.78658370498, lng:-74.0188942764609}, { lat: 41.786997305975, lng:-74.0184773666654}, { lat: 41.7877746522982, lng:-74.0182309417159}, { lat: 41.7884146390067, lng:-74.0186099600872}, { lat: 41.7889754083541, lng:-74.0187115000491}, { lat: 41.7896440502945, lng:-74.0185913980577}, { lat: 41.7903417311076, lng:-74.0180161540568}, { lat: 41.7909830110463, lng:-74.01807837498}, { lat: 41.798247508421, lng:-74.0382775883925}, { lat: 41.7983526660564, lng:-74.0385700888604}, { lat: 41.7984788656701, lng:-74.0389211224746}, { lat: 41.798716701938, lng:-74.0395826935571}, { lat: 41.7988059935565, lng:-74.0398310672228}, { lat: 41.8006122627667, lng:-74.0450229350875}];
				var Plutarch = new google.maps.Polygon({
					paths: plut,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#753742',
					fillOpacity: .4
				});
				Plutarch.setMap(map);
			} else if (hamlet == 8){
				var spring = [{ lat: 41.8004129702205, lng:-74.0846506807371}, { lat: 41.798131631104, lng:-74.1163195339249}, { lat: 41.7892057387205, lng:-74.1254825957057}, { lat: 41.7872401413847, lng:-74.1275700792447}, { lat: 41.774930232942, lng:-74.0898708432148}, { lat: 41.7754403599201, lng:-74.0892268203538}, { lat: 41.7762328306387, lng:-74.0884761218963}, { lat: 41.7769568332594, lng:-74.087434299277}, { lat: 41.7773441261396, lng:-74.0865865653015}, { lat: 41.7775368815891, lng:-74.0861646395925}, { lat: 41.7776262149292, lng:-74.0860311793741}, { lat: 41.7777398709452, lng:-74.0858613812295}, { lat: 41.7779189845173, lng:-74.0855937897661}, { lat: 41.7780151852595, lng:-74.0854500671092}, { lat: 41.7780576609131, lng:-74.0853866088108}, { lat: 41.7784846926474, lng:-74.0845558319292}, { lat: 41.7786285487927, lng:-74.084109122344}, { lat: 41.7788558461822, lng:-74.0834231428936}, { lat: 41.7790020459884, lng:-74.0830727132421}, { lat: 41.779127776951, lng:-74.0827713424791}, { lat: 41.7791930787612, lng:-74.0826148161182}, { lat: 41.7792477106489, lng:-74.0824838645798}, { lat: 41.7793545780016, lng:-74.0821952994143}, { lat: 41.7795284417131, lng:-74.081696135978}, { lat: 41.7798304790475, lng:-74.0807614339059}, { lat: 41.7798937297603, lng:-74.0805656905011}, { lat: 41.7808164340818, lng:-74.0801453366395}, { lat: 41.7815375106345, lng:-74.0802539134894}, { lat: 41.7821438374595, lng:-74.0808195560551}, { lat: 41.783048414173, lng:-74.0813861101608}, { lat: 41.7839282103258, lng:-74.081290391983}, { lat: 41.7845189318164, lng:-74.0809341221705}, { lat: 41.7847674798795, lng:-74.0807842182429}, { lat: 41.7850725739925, lng:-74.0805454652956}, { lat: 41.7854068387315, lng:-74.0802838816273}, { lat: 41.7857993115588, lng:-74.0799683326798}, { lat: 41.786355034293, lng:-74.0794963774395}, { lat: 41.787911949838, lng:-74.0791647833609}, { lat: 41.7884703673771, lng:-74.0793428510539}, { lat: 41.7886218801099, lng:-74.079415966311}, { lat: 41.7891652755183, lng:-74.0796781947369}, { lat: 41.7894048009826, lng:-74.079849137566}, { lat: 41.7896371486076, lng:-74.0800149591425}, { lat: 41.7900008505641, lng:-74.0802745282444}, { lat: 41.7908005135263, lng:-74.0802699692158}, { lat: 41.7915042775416, lng:-74.0803405247807}, { lat: 41.791930534127, lng:-74.080376157077}, { lat: 41.7929611038846, lng:-74.0804299101947}, { lat: 41.7938028990216, lng:-74.080996975388}, { lat: 41.7958339947612, lng:-74.0824204906998}, { lat: 41.7969026334123, lng:-74.0832769729568}, { lat: 41.7975634881592, lng:-74.0838469610466}, { lat: 41.7982315202118, lng:-74.0843021098734}, { lat: 41.7990694443661, lng:-74.0846459174379}, { lat: 41.8004129702205, lng:-74.0846506807371}];
				var Springtown = new google.maps.Polygon({
					paths: spring,
					strokeColor: '#000',
					strokeOpacity: 0.8,
					strokeWeight: 3,
					fillColor: '#AA5042',
					fillOpacity: .4
				});
				Springtown.setMap(map);
			} else if (hamlet == 9) {
				var down = [{ lat: 41.744938, lng:-74.086754},{ lat: 41.745308, lng:-74.087677},{ lat: 41.745602, lng:-74.087502},{ lat: 41.745712, lng:-74.088013},{ lat: 41.746479, lng:-74.087708},{ lat: 41.746666, lng:-74.087761},{ lat: 41.746693, lng:-74.087784},{ lat: 41.746532, lng:-74.088348},{ lat: 41.746429, lng:-74.088326},{ lat: 41.746433, lng:-74.088707},
				{ lat: 41.746365, lng:-74.088737},{ lat: 41.746387, lng:-74.088905},{ lat: 41.746246, lng:-74.088943},{ lat: 41.746288, lng:-74.089149},{ lat: 41.746937, lng:-74.088936},{ lat: 41.746799, lng:-74.089432},{ lat: 41.747288, lng:-74.089226},{ lat: 41.747982, lng:-74.088615},{ lat: 41.750137, lng:-74.087326},{ lat: 41.749989, lng:-74.087135},{ lat: 41.74971, lng:-74.086594},{ lat: 41.750137, lng:-74.086334},{ lat: 41.750408, lng:-74.086662},{ lat: 41.750629, lng:-74.086555},{ lat: 41.75058, lng:-74.086372},{ lat: 41.750824, lng:-74.086288},{ lat: 41.750694, lng:-74.085854},{ lat: 41.75087, lng:-74.085762},{ lat: 41.751057, lng:-74.08606},{ lat: 41.751198, lng:-74.08596},{ lat: 41.751198, lng:-74.086029},{ lat: 41.75153, lng:-74.086052},{ lat: 41.751534, lng:-74.086136},{ lat: 41.751671, lng:-74.086121},{ lat: 41.751671, lng:-74.086197},{ lat: 41.751945, lng:-74.086037},{ lat: 41.751892, lng:-74.084816},{ lat: 41.751144, lng:-74.084869},{ lat: 41.750378, lng:-74.085297},{ lat: 41.750217, lng:-74.085342},{ lat: 41.749817, lng:-74.085625},{ lat: 41.749481, lng:-74.085625},{ lat: 41.749393, lng:-74.085358},{ lat: 41.749172, lng:-74.085487},{ lat: 41.749016, lng:-74.085594},{ lat: 41.748447, lng:-74.084114},{ lat: 41.748219, lng:-74.083534},{ lat: 41.747917, lng:-74.08371},{ lat: 41.747311, lng:-74.081993},{ lat: 41.746613, lng:-74.082481},{ lat: 41.747158, lng:-74.083893},{ lat: 41.747318, lng:-74.083794},{ lat: 41.747444, lng:-74.084145},{ lat: 41.746929, lng:-74.08448},{ lat: 41.747028, lng:-74.084602},{ lat: 41.747547, lng:-74.084785},{ lat: 41.747623, lng:-74.084816},{ lat: 41.747543, lng:-74.08532},{ lat: 41.747257, lng:-74.085434},{ lat: 41.747368, lng:-74.085739},{ lat: 41.747063, lng:-74.085846},{ lat: 41.747047, lng:-74.085808},{ lat: 41.746944, lng:-74.085861},{ lat: 41.746948, lng:-74.085899},{ lat: 41.74667, lng:-74.085991},{ lat: 41.746719, lng:-74.086227},{ lat: 41.746262, lng:-74.086349},{ lat: 41.745964, lng:-74.086449},{ lat: 41.744938, lng:-74.086754}];
				var DowntownDistrict = new google.maps.Polygon({
					paths: down,
					strokeColor: '#000',
					strokeOpacity: 1,
					strokeWeight: 3,
					fillColor: '#D8BD8A',
					fillOpacity: .67
				});
				DowntownDistrict.setMap(map);
			} else if (hamlet == 10) {
				var jenkin = [{ lat: 41.7395521363567, lng:-74.1051853581908}, { lat: 41.7369403422355, lng:-74.1064861349083}, { lat: 41.7357489784671, lng:-74.106672025601}, { lat: 41.7339897937974, lng:-74.1069466610723}, { lat: 41.7327883212973, lng:-74.1068789536021}, { lat: 41.7311774718591, lng:-74.1067881048741}, { lat: 41.7297027138497, lng:-74.107401059513}, { lat: 41.7285853920891, lng:-74.108501547315}, { lat: 41.7278759418854, lng:-74.1092005634417}, { lat: 41.7270597742981, lng:-74.1100051443188}, { lat: 41.7263194184748, lng:-74.1106481122546}, { lat: 41.7254431717547, lng:-74.1114099208028}, { lat: 41.7247111203781, lng:-74.1124074716503}, { lat: 41.7238799774758, lng:-74.1135393971659}, { lat: 41.7231005757623, lng:-74.1146020038121}, { lat: 41.7173886321879, lng:-74.1030296645282}, { lat: 41.7163623187421, lng:-74.104228738365}, { lat: 41.7158458386743, lng:-74.1039786178425}, { lat: 41.7151742079941, lng:-74.1036528461781}, { lat: 41.7147309701738, lng:-74.1039289259794}, { lat: 41.7135546027474, lng:-74.1046626420748}, { lat: 41.7126700593612, lng:-74.105069829508}, { lat: 41.7114927141622, lng:-74.1058759696494}, { lat: 41.7084910162258, lng:-74.1073315113921}, { lat: 41.7075367298853, lng:-74.1057351022187}, { lat: 41.7063552842051, lng:-74.1037587984002}, { lat: 41.7054628616652, lng:-74.1022660532535}, { lat: 41.7037138733117, lng:-74.0997123661789}, { lat: 41.7029512503081, lng:-74.0985762405983}, { lat: 41.7022456347496, lng:-74.0975250810891}, { lat: 41.7008573896877, lng:-74.0954571129159}, { lat: 41.699910161962, lng:-74.0939704892198}, { lat: 41.6984008743746, lng:-74.091601894171}, { lat: 41.6967812856941, lng:-74.0890604059422}, { lat: 41.6949999261839, lng:-74.0862704944992}, { lat: 41.6929428103272, lng:-74.0830531315338}, { lat: 41.6929924618734, lng:-74.0830220950076}, { lat: 41.6930703311045, lng:-74.0829794521724}, { lat: 41.6952135633897, lng:-74.0817690290391}, { lat: 41.6970913495943, lng:-74.0805156343767}, { lat: 41.6989960011263, lng:-74.0791912212247}, { lat: 41.7003862204565, lng:-74.0782244650656}, { lat: 41.7024780758079, lng:-74.0768553402589}, { lat: 41.7030434926462, lng:-74.07643505487}, { lat: 41.7042711519586, lng:-74.0757985552873}, { lat: 41.7049792760277, lng:-74.0753578591276}, { lat: 41.706411469251, lng:-74.0742841997512}, { lat: 41.7073522420461, lng:-74.0735789085085}, { lat: 41.7081555434143, lng:-74.0731367793691}, { lat: 41.7088576147883, lng:-74.0730437670318}, { lat: 41.7102728244792, lng:-74.0728690150576}, { lat: 41.7115489296968, lng:-74.0724911230905}, { lat: 41.7130536474016, lng:-74.0718899959189}, { lat: 41.7139993647143, lng:-74.0714143560564}, { lat: 41.7161466786058, lng:-74.076981373835}, { lat: 41.7271844800283, lng:-74.069713388547}, { lat: 41.7297487757647, lng:-74.0757991359445}, { lat: 41.7341351668904, lng:-74.0877850603256}, { lat: 41.7323724750181, lng:-74.0893901303852}, { lat: 41.7395521363567, lng:-74.1051853581908}];
                    var Jenkinstown = new google.maps.Polygon({
						paths: jenkin,
                        strokeColor: '#000',
                        strokeOpacity: 1,
                        strokeWeight: 3,
                        fillColor: '#D8D78F',
                        fillOpacity: .4
                    });
                    Jenkinstown.setMap(map);
				} else {
					var paltz = [{ lat: 41.7680529770465, lng:-74.092445627064}, { lat: 41.7664256746693, lng:-74.0924658965537}, { lat: 41.765242197051, lng:-74.0924825757092}, { lat: 41.7631711106174, lng:-74.0925117618282}, { lat: 41.7618396975057, lng:-74.0925305227182}, { lat: 41.7603665889129, lng:-74.0933416864683}, { lat: 41.7588919158788, lng:-74.0939552371165}, { lat: 41.7575698435379, lng:-74.0951593940882}, { lat: 41.7566946703031, lng:-74.0967524132918}, { lat: 41.7552153189745, lng:-74.0967731565335}, { lat: 41.7541735575802, lng:-74.0959974312262}, { lat: 41.7532766138759, lng:-74.0948245364722}, { lat: 41.752678644857, lng:-74.0940426247766}, { lat: 41.7519327355072, lng:-74.0932628096682}, { lat: 41.7490961310642, lng:-74.1089105546325}, { lat: 41.7404182720897, lng:-74.1155493906126}, { lat: 41.7369403422355, lng:-74.1064861349083}, { lat: 41.7395521363567, lng:-74.1051853581908}, { lat: 41.7323724750181, lng:-74.0893901303852}, { lat: 41.7341351668904, lng:-74.0877850603256}, { lat: 41.7297487757647, lng:-74.0757991359445}, { lat: 41.7422674033544, lng:-74.0687055158443}, { lat: 41.7418315266936, lng:-74.0672032526837}, { lat: 41.7447323878952, lng:-74.0652796772895}, { lat: 41.747838784472, lng:-74.0638312887724}, { lat: 41.7518511792656, lng:-74.0622371159143}, { lat: 41.7549286288622, lng:-74.0610142634095}, { lat: 41.7579644219615, lng:-74.0704545395454}, { lat: 41.7604681002939, lng:-74.0690351911402}, { lat: 41.7650031611092, lng:-74.0810242095043}, { lat: 41.7680529770465, lng:-74.092445627064}];
                    var NewPaltz = new google.maps.Polygon({
						paths: paltz,
                        strokeColor: '#000',
                        strokeOpacity: 1,
                        strokeWeight: 3,
                        fillColor: '#D4CBE5',
                        fillOpacity: .4
                    });
                    NewPaltz.setMap(map);
				}

			


			const hrefFix_to = (id) => ("item.php?pkname=cid&id=" + id + "&table=a_all_items");
			const build_content_popup = (title, data, id, landmark) => {
				let content = `<table><tr><td>Address:</td><td>${title}</td></tr>`;

				if (data["Date of Construction"])
					content += `<tr><td>Constructed in:</td><td>${data["Date of Construction"]}</td></tr>`

				if (data["Historic Use"])
					content += `<tr><td>Historic Use:</td><td>${data["Historic Use"]}</td></tr>`

				if (data["Present Use"])
					content += `<tr><td>Present Use:</td><td>${data["Present Use"]}</td></tr>`
				
				if (landmark == 0)
					content += `<tr><td>Landmark Status:</td><td>Not Designated</td></tr>`
				else
					content += `<tr><td>Landmark Status:</td><td>Designated</td></tr>`

				content += `</table><br><a href=${hrefFix_to(id)}><strong>Read More</strong></a>`
				return content
			}
			const MyLatLng = items[hamlet-1].coords;
			let infoWindow = new google.maps.InfoWindow({
				content: "Click on a marker to get started.",
				position: MyLatLng
			})

			infoWindow.open(map);

			const mapInfoToMarker = (title = "test", data = "test", coords, id, landmark) => {

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


			//legend
			const legend = document.getElementById("legend");

			//landmark status
			const div_landmark = document.createElement("div");
			div_landmark.innerHTML='<img src="/p/s23-06/images/pins/yellowpin.png"> <strong>Landmark</strong><hr>'
			legend.appendChild(div_landmark);

			//Wood frame
			const div_wood = document.createElement("div");
			div_wood.innerHTML='<img src="/p/s23-06/images/pins/woodpin.png"> <strong>Wood Frame</strong><hr>'
			legend.appendChild(div_wood);

			//Brick
			const div_brick = document.createElement("div");
			div_brick.innerHTML='<img src="/p/s23-06/images/pins/brickredpin.png"> <strong>Brick</strong><hr>'
			legend.appendChild(div_brick);

			//Stone
			const div_stone = document.createElement("div");
			div_stone.innerHTML='<img src="/p/s23-06/images/pins/darkgreenpin.png"> <strong>Stone</strong><hr>'
			legend.appendChild(div_stone);

			//After 1930
			const div_1930 = document.createElement("div");
			div_1930.innerHTML='<img src="/p/s23-06/images/pins/graypin.png"> <strong>Built after 1930</strong><hr>'
			legend.appendChild(div_1930);

			//Default
			const div_default = document.createElement("div");
			div_default.innerHTML='<img src="/p/s23-06/images/pins/whitepin.png"> <strong>No Data</strong>'
			legend.appendChild(div_default);

			//Bind legend to map
			map.controls[google.maps.ControlPosition.RIGHT_TOP].push(legend);


			marker.forEach((mark) => {
				if (mark.coords.lat != 0 && mark.coords.lng != 0) {
					mapInfoToMarker(mark.title, mark.data, mark.coords, mark.cid, mark.landmark);
				}
			})


		}
	</script>
	<?php } ?>

	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBF14neTAjoLNgyXlo8egPpdPkP07hVbmc&callback=initMap&v=weekly" defer></script>
	<script src="" async defer></script>
</body>

</html>