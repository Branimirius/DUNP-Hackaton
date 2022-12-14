import "mapbox-gl/dist/mapbox-gl.css";
import Map, {
    Marker,
    NavigationControl,
    FullscreenControl,
    GeolocateControl,
    MarkerDragEvent,
} from "react-map-gl";
import { useCallback, useState } from "react";
import { Content } from "./styles";
import { LocationDetail } from "../../components/LocationDetail/LocationDetail";


const TOKEN = 'pk.eyJ1Ijoiam92YW5qZW5qaWMiLCJhIjoiY2wzdWJvNG4wMGZ2YjNkcGZ2dm5kZm5nYyJ9.9bCbz74PqDnzQDpBqRenHw'

export const NewLocationPage = () => {
    const initialLng = 20.5181;
    const initialLat = 43.1407;

    const [marker, setMarker] = useState({
        latitude: 43.1407,
        longitude: 20.5181
    });

    const onMarkerDragEnd = useCallback((event: MarkerDragEvent) => {
        setMarker({
            latitude: event.lngLat.lng,
            longitude: event.lngLat.lat
        })

        console.log(event.lngLat.lng);
        console.log(event.lngLat.lat);

    }, []);

    return (
        <Content>
            <Map
                mapboxAccessToken={TOKEN}
                style={{
                    width: "70%",
                    height: "600px",
                    borderRadius: "15px",
                }}
                initialViewState={{
                    longitude: initialLng,
                    latitude: initialLat,
                    zoom: 13
                }}
                mapStyle="mapbox://styles/mapbox/streets-v9"
            >
                <Marker
                    color="red"
                    longitude={initialLng}
                    latitude={initialLat}
                    anchor="bottom"
                    draggable
                    onDragEnd={onMarkerDragEnd} >
                </Marker>
                <NavigationControl position="bottom-right" />
                <FullscreenControl />
                <GeolocateControl />
            </Map>

            <LocationDetail long={marker.longitude} lat={marker.latitude} />
        </Content>
    );
}
