import { useEffect, useState } from "react";
import { createComment, getLocationComments, getLocationImage } from "../../services/apis";
import { Container, HorizontalLine } from "./styles";
import { Image } from "./styles";

const COMMENTS = [{
    username: 'mirko123',
    commentText: 'Jako lepa destinacija.'
}, {
    username: 'nale',
    commentText: 'Jako lepa destinacija.'
}, {
    username: 'steva',
    commentText: 'Veoma lepo.'
}, {
    username: 'nikola',
    commentText: 'Ne svidja mi se.'
},]

export const SelectedLocation: React.FC<{ location: any }> = ({ location }) => {

    const [comments, setComments] = useState<any>(COMMENTS);
    const [imageUrl, setImageUrl] = useState<any>(null);

    const fetchComments = async () => {
        const response = await getLocationComments(location.id);
        setComments(response);
    };

    const fetchImage = async () => {
        const response = await getLocationImage(location.imageUrl);
        setImageUrl(response.data);
    };

    useEffect(() => {
        fetchImage();
        //fetchComments();
    }, [location])

    const addCommentHandler = async (event: React.FormEvent) => {
        event.preventDefault();
        const response = await createComment({
            geoEntityId: location.id,
            userId: 1,
            commentText: location.description
        })

        //fetchComments();
    }

    return <Container>
        {imageUrl && <Image src={require('../../images/noviPazar.jpg')} />}
        <p>{location.description} 👍</p>
        <HorizontalLine />
        {comments.map((comment: any) => <div key={comment.id}>
            <p>{comment.commentText} 👍</p>
            <p>by {comment.username}</p>
        </div>)}
        <form onSubmit={addCommentHandler}>
            <input type="text" />
            <button type="submit">Add</button>
        </form>

    </Container>
};