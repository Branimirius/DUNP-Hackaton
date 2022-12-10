import "mapbox-gl/dist/mapbox-gl.css";
import Map, {
    Marker,
    NavigationControl,
    FullscreenControl,
    GeolocateControl,
} from "react-map-gl";
import { useState } from "react";


const TOKEN = 'pk.eyJ1Ijoiam92YW5qZW5qaWMiLCJhIjoiY2wzdWJvNG4wMGZ2YjNkcGZ2dm5kZm5nYyJ9.9bCbz74PqDnzQDpBqRenHw'

const CityMapPage = () => {
    const [lng, setLng] = useState(20.5181);
    const [lat, setLat] = useState(43.1407);

    return (
        <>
            <Map
                mapboxAccessToken={TOKEN}
                style={{
                    width: "70%",
                    height: "600px",
                    borderRadius: "15px",
                    border: "2px solid red",
                    marginLeft: "30px",
                    marginTop: "30px"
                }}
                initialViewState={{
                    longitude: lng,
                    latitude: lat,
                }}
                mapStyle="mapbox://styles/mapbox/streets-v9"
            >
                <Marker longitude={lng} latitude={lat} />
                <Marker longitude={lng + 2} latitude={lat} />
                <Marker longitude={lng + 10} latitude={lat} />
                <Marker longitude={lng + 19} latitude={lat} />
                <Marker longitude={lng + 11} latitude={lat} />
                <Marker longitude={lng + 12} latitude={lat} />
                <Marker longitude={lng + 21} latitude={lat} />
                <Marker longitude={lng + 31} latitude={lat} />
                <Marker longitude={lng + 44} latitude={lat} />
                <NavigationControl position="bottom-right" />
                <FullscreenControl />

                <GeolocateControl />
            </Map>
        </>
    );
}

export default CityMapPage;