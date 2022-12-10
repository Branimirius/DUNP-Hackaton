import { useRef } from "react";
import { createLocation } from "../../services/apis";

export const LocationDetail: React.FC<{ long: number, lat: number }> = ({ long, lat }) => {

    const descriptionRef = useRef<HTMLInputElement>(null);

    const createLocationHandler = async (event: React.FormEvent) => {
        event.preventDefault();

        const response = await createLocation({ category: 'FOOD', latitude: lat, longitude: long, description: descriptionRef.current!.value })

        console.log(response.data);
    }

    return (
        <form onSubmit={createLocationHandler}>
            <div>
                <p>Longitude: {long}</p>
                <p>Latitude: {lat}</p>
            </div>
            <div>
                <p>Upload image: </p>
                <input type="file" />
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

