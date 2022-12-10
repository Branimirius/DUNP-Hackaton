import { useContext, useEffect, useRef, useState } from "react";
import { createComment, getLocationComments, getLocationImage } from "../../services/apis";
import AuthContext from "../../store/auth-context";
import { Container, HorizontalLine } from "./styles";
import { Image } from "./styles";

export const SelectedLocation: React.FC<{ location: any }> = ({ location }) => {

    const { isLoggedIn } = useContext(AuthContext);

    const [comments, setComments] = useState<any>([]);
    const [imageUrl, setImageUrl] = useState<any>(null);

    let commentText = useRef<HTMLInputElement>(null);

    const fetchComments = async () => {
        const response = await getLocationComments(location.id);
        setComments(response.data);
    };

    const fetchImage = async () => {
        const response = await getLocationImage(location.imageUrl);
        setImageUrl(response.data);
    };

    useEffect(() => {
        fetchImage();
        fetchComments();
    }, [location])

    const addCommentHandler = async (event: React.FormEvent) => {
        event.preventDefault();
        const response = await createComment({
            geoEntityId: location.id,
            userId: 1,
            commentText: commentText.current?.value
        })

        commentText.current!.value = '';

        fetchComments();
    }

    return <Container>
        {imageUrl && <Image src={require('../../images/noviPazar.jpg')} />}
        {isLoggedIn && <p>{location.description} 👍</p>}
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