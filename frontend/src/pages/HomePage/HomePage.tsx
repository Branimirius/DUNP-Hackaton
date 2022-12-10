import { Content, Image, SearchBarContainer, WelcomeMessage } from "./styles";
import { useState } from "react";
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