import "mapbox-gl/dist/mapbox-gl.css";
import Map, {
    Marker,
    NavigationControl,
    FullscreenControl,
    GeolocateControl,
} from "react-map-gl";
import { useEffect, useState } from "react";
import { deleteLocation, filterLocations, getAllLocations } from "../../services/apis";
import { Content } from "./styles";
import { SelectedLocation } from "../../components/SelectedLocation/SelectedLocation";
import { SearchBarContainer } from "../HomePage/styles";


const TOKEN = 'pk.eyJ1Ijoiam92YW5qZW5qaWMiLCJhIjoiY2wzdWJvNG4wMGZ2YjNkcGZ2dm5kZm5nYyJ9.9bCbz74PqDnzQDpBqRenHw'

const CityMapPage = () => {
    const [lng, setLng] = useState(20.5181);
    const [lat, setLat] = useState(43.1407);

    const [location, setLocation] = useState<any>(null);

    const [locations, setLocations] = useState<any>([]);

    const [userinfo, setUserInfo] = useState<any>({
        languages: [],
    });

    const handleChange = async (e: any) => {
        // Destructuring
        const { value, checked } = e.target;
        const { languages } = userinfo;


        // Case 1 : The user checks the box
        if (checked) {
            setUserInfo({
                languages: [...languages, value],
                response: [...languages, value],
            });

            const res = await filterLocations([...languages, value]);

            console.log(res.data);
        }

        // Case 2  : The user unchecks the box
        else {
            setUserInfo({
                languages: languages.filter((e: any) => e !== value),
                response: languages.filter((e: any) => e !== value),
            });

            const res = await filterLocations((e: any) => e !== value);

            console.log(res.data);
        }
    };

    const fetchLocations = async () => {
        const response = await getAllLocations();
        console.log(response.data);
        setLocations(response.data)
    };

    useEffect(() => {
        fetchLocations();
    }, [])

    const deleteLocationHandler = async () => {
        const response = await deleteLocation(location.id);

        console.log(response.data)

        fetchLocations();
        setLocation(null);
    }

    return (
        <>
            <SearchBarContainer>
                <input
                    className="form-check-input"
                    type="checkbox"
                    name="languages"
                    value="ANIMAL"
                    id="a"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="a"
                >
                    Animal
                </label>
                <input
                    className="form-check-input"
                    type="checkbox"
                    name="languages"
                    value="PLANT"
                    id="b"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="b"
                >
                    Plant
                </label>
                <input
                    className="form-check-input"
                    type="checkbox"
                    name="languages"
                    value="FOOD"
                    id="c"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="c"
                >
                    Food
                </label>
                <input
                    className="form-check-input"
                    type="checkbox"
                    name="languages"
                    value="MONUMENT"
                    id="d"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="d"
                >
                    Monument
                </label>
                <input
                    className="form-check-input"
                    type="checkbox"
                    name="BUILDING"
                    value="C#"
                    id="g"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="g"
                >
                    Building
                </label>

                <input
                    className="form-check-input"
                    type="checkbox"
                    name="languages"
                    value="NATURE"
                    id="h"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="h"
                >
                    Nature
                </label>
                <input
                    className="form-check-input"
                    type="checkbox"
                    name="RESTAURANT"
                    value="C"
                    id="r"
                    onChange={handleChange}
                />
                <label
                    className="form-check-label"
                    htmlFor="r"
                >
                    Restaurant
                </label>

            </SearchBarContainer>
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

                {location && <SelectedLocation location={location} onDeleteHandler={deleteLocationHandler} />}
            </Content>
        </>
    );
}

export default CityMapPage;