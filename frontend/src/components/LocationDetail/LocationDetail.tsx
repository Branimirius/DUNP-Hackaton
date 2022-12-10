import { ChangeEvent, useRef, useState } from "react";
import { useHistory } from "react-router-dom";
import { createLocation, uploadImage } from "../../services/apis";

export const LocationDetail: React.FC<{ long: number, lat: number }> = ({ long, lat }) => {

    const history = useHistory();
    const [file, setFile] = useState<File>();
    const descriptionRef = useRef<HTMLInputElement>(null);

    const createLocationHandler = async (event: React.FormEvent) => {
        event.preventDefault();
        const locationResponse = await createLocation({ userId: 1, category: 'FOOD', latitude: lat, longitude: long, description: descriptionRef.current!.value })

        const imageResponse = await uploadImage(locationResponse.data.id, file);

        console.log(imageResponse.data);

        history.push('/map');
    }

    const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setFile(e.target.files[0]);

            //get category
        }
    };

    return (
        <form onSubmit={createLocationHandler}>
            <div>
                <p>Longitude: {long}</p>
                <p>Latitude: {lat}</p>
            </div>
            <div>
                <p>Upload image: </p>
                <input type="file" onChange={handleFileChange} />
            </div>
            <div>
                <p>Cattegory: FETCH FROM BACKEND</p>
            </div>
            <div>
                <input type="text" ref={descriptionRef} />
            </div>
            <div>
                <button type="submit">SAVE</button>
            </div>
        </form>
    )
};

