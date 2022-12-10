import { Content, Image, WelcomeMessage } from "./styles";

export const HomePage = () => {
    return (
        <>
            <Image src={require('../../images/noviPazar.jpg')} alt="Novi Pazar" />
            <Content>
                <WelcomeMessage> Welcome to Novi Pazar! </WelcomeMessage>
            </Content>
        </>
    )
};