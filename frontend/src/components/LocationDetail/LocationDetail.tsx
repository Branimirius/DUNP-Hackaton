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

/*

import React, { useState } from 'react';

const ImageComponent = () => {
  const [imageSrc, setImageSrc] = useState(null);

  // Read the image file and convert it to a byte array
  const readImage = (file) => {
    const reader = new FileReader();

    reader.onloadend = () => {
      setImageSrc(reader.result);
    }

    reader.readAsArrayBuffer(file);
  }

  return (
    <>
      <input type="file" onChange={(e) => readImage(e.target.files[0])} />

      {imageSrc && <img src={imageSrc} />}
    </>
  );
}*/

