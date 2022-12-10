import { useContext, useEffect, useRef, useState } from "react";
import { createComment, getLocationComments, getLocationImage } from "../../services/apis";
import AuthContext from "../../store/auth-context";
import { Container, HorizontalLine } from "./styles";
import { Image } from "./styles";

import classes from './SelectedLocation.module.css';

export const SelectedLocation: React.FC<{ location: any, onDeleteHandler: (locationId: number) => {} }> = ({ location, onDeleteHandler }) => {

    const { isLoggedIn } = useContext(AuthContext);

    const [imageSrc, setImageSrc] = useState<any>();

    let userId = localStorage.getItem('id') || '';

    const [comments, setComments] = useState<any>([]);

    let commentText = useRef<HTMLInputElement>(null);


    const fetchComments = async () => {
        const response = await getLocationComments(location.id);
        setComments(response.data);
    };

    const fetchImage = async () => {
        const response = await getLocationImage(location.imageUrl);

        setImageSrc(URL.createObjectURL(response.data));

    };

    useEffect(() => {
        fetchImage();
        fetchComments();
    }, [location])

    const addCommentHandler = async (event: React.FormEvent) => {
        event.preventDefault();
        const response = await createComment({
            geoEntityId: location.id,
            userId: localStorage.getItem('id'),
            commentText: commentText.current?.value
        })

        commentText.current!.value = '';

        fetchComments();
    }

    const deleteLocationHandler = async () => {
        onDeleteHandler(location.id);
    }

    //onClick={deleteLocationHandler}

    return <Container>
        <img src={imageSrc} alt="img" />
        {isLoggedIn && <p>{location.description}👍</p>}
        {isLoggedIn && userId === location.userId.toString() && <section className={classes.registerButton} onClick={deleteLocationHandler}>
            <p className={classes.newAccount}>Delete entity</p>
        </section>}
        <HorizontalLine />
        {comments.map((comment: any) => <div key={comment.id}>
            <p>{comment.commentText}</p>
            {isLoggedIn && <>👍</>}
            <p>by {comment.username}</p>
        </div>)}
        {isLoggedIn && <form onSubmit={addCommentHandler}>
            <input type="text" ref={commentText} />
            <button type="submit">Add</button>
        </form>}

    </Container>
};