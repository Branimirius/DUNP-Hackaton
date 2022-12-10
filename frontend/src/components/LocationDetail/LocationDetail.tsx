import { ChangeEvent, useRef, useState } from "react";
import { useHistory } from "react-router-dom";
import { checkImageCategory, createLocation, uploadImage } from "../../services/apis";

export const LocationDetail: React.FC<{ long: number, lat: number }> = ({ long, lat }) => {

    const history = useHistory();
    const [file, setFile] = useState<File>();
    const descriptionRef = useRef<HTMLInputElement>(null);

    const createLocationHandler = async (event: React.FormEvent) => {
        event.preventDefault();

        let id = localStorage.getItem('id') || '';

        const locationResponse = await createLocation({ userId: id, category: 'FOOD', latitude: lat, longitude: long, description: descriptionRef.current!.value })

        const imageResponse = await uploadImage(locationResponse.data.id, file);

        console.log(imageResponse.data);

        history.push('/map');
    }

    const handleFileChange = async (e: ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            setFile(e.target.files[0]);

            //get category
            const categoryResponse = await checkImageCategory(file);

            console.log(categoryResponse.data);
        }
    };

    return (
        <form onSubmit={createLocationHandler}>
            <div>
                <p>Longitude: {long}</p>
                <p>Latitude: {lat}</p>
            </div>
            <div style={{backgroundColor: "#f2f2f2", padding: "8px 10px", borderRadius: "10px"}} >
                <p>Upload image: </p>
                <input type="file" onChange={handleFileChange} />
            </div>
            <div>
                <p>Category: FETCH FROM BACKEND</p>
            </div>
            <div>
                <input style={{width: "100%", height: "50px"}} type="text" ref={descriptionRef} />
            </div>
            <div>
                <button style={{
                    background: "#eaa79e",
                    border: "none",
                    color: "white",
                    padding: "15px 32px",
                    textAlign: "center",
                    textDecoration: "none",
                    display: "inline-block",
                    fontSize: "16px",
                    margin: "4px 2px",
                    cursor: "pointer",
                    borderRadius: "100px",
                    marginTop: "15px"
                }} type="submit">SAVE</button>
            </div>
        </form>
    )
};

