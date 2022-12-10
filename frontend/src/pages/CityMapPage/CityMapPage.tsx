import "mapbox-gl/dist/mapbox-gl.css";
import Map, {
    Marker,
    NavigationControl,
    FullscreenControl,
    GeolocateControl,
} from "react-map-gl";
import { useEffect, useState } from "react";
import { getAllLocations } from "../../services/apis";
import { useHistory } from "react-router-dom";


const TOKEN = 'pk.eyJ1Ijoiam92YW5qZW5qaWMiLCJhIjoiY2wzdWJvNG4wMGZ2YjNkcGZ2dm5kZm5nYyJ9.9bCbz74PqDnzQDpBqRenHw'

const CityMapPage = () => {
    const [lng, setLng] = useState(20.5181);
    const [lat, setLat] = useState(43.1407);

    const [locations, setLocations] = useState<any>([]);

    useEffect(() => {
        const fetchLocations = async () => {
            const response = await getAllLocations();

            console.log(response.data);

            setLocations(response.data)
        };

        fetchLocations();
    }, [])

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
                    zoom: 13
                }}
                mapStyle="mapbox://styles/mapbox/streets-v9"
            >
                {locations && locations.map((location: { id: number | undefined, latitude: number | undefined; longitude: number | undefined; }) => <Marker key={location.id} longitude={location.latitude} latitude={location.longitude} />)}


                <NavigationControl position="bottom-right" />
                <FullscreenControl />

                <GeolocateControl />
            </Map>
        </>
    );
}

export default CityMapPage;