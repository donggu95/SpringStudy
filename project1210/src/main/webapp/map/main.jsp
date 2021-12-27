<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<div id="googleMap" style="width:100%;height:700px;"></div>

<script>
function initMap() {
	var mapProp= {
	  center:new google.maps.LatLng(37.500031818495806, 127.03547345605348),
	  zoom:15,
	};
	
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	//마커 적용하기 
	var marker = new google.maps.Marker({
			position: mapProp.center,
			animation:google.maps.Animation.BOUNCE,
			icon:{
				url:'/images/smile.png',
				scaledSize: new google.maps.Size(50,50)
			}
		});
	marker.setMap(map);
	
	//메시지 창 띄우기
	var infowindow = new google.maps.InfoWindow({
		  content:"여기 역삼맛집이에요"
		});

		infowindow.open(map,marker);
}
</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4WzOcp3aPl8KC_YIyS3_3FVKqTw8Ml64
&callback=initMap"></script>

</body>
</html>




