import "mapbox-gl/dist/mapbox-gl.css";
import Map, {
    Marker,
    NavigationControl,
    FullscreenControl,
    GeolocateControl,
} from "react-map-gl";
import { useEffect, useState } from "react";
import { getAllLocations } from "../../services/apis";
import { Content } from "./styles";
import { SelectedLocation } from "../../components/SelectedLocation/SelectedLocation";


const TOKEN = 'pk.eyJ1Ijoiam92YW5qZW5qaWMiLCJhIjoiY2wzdWJvNG4wMGZ2YjNkcGZ2dm5kZm5nYyJ9.9bCbz74PqDnzQDpBqRenHw'

const CityMapPage = () => {
    const [lng, setLng] = useState(20.5181);
    const [lat, setLat] = useState(43.1407);

    const [location, setLocation] = useState<any>(null);

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
        <Content>
            <Map
                mapboxAccessToken={TOKEN}
                style={{
                    height: "600px",
                    minWidth: "70%",
                    borderRadius: "15px",
                    border: "2px solid red",
                }}
                initialViewState={{
                    longitude: lng,
                    latitude: lat,
                    zoom: 13
                }}
                mapStyle="mapbox://styles/mapbox/streets-v9"
            >
                {locations && locations.map((location: { id: number, latitude: number; longitude: number; }) =>
                    <Marker key={location!.id} longitude={location!.latitude} latitude={location!.longitude} onClick={e => {
                        e.originalEvent.stopPropagation();
                        setLocation(location);
                    }} />)}

                <Marker
                    color="red"
                    longitude={lng}
                    latitude={lat}
                    anchor="bottom"
                    draggable>
                </Marker>


                <NavigationControl position="bottom-right" />
                <FullscreenControl />

                <GeolocateControl />
            </Map>

            {location && <SelectedLocation location={location} />}
        </Content>
    );
}

export default CityMapPage;