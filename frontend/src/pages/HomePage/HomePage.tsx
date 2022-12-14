import { Content, ExploreMessage, WelcomeMessage } from "./styles";
import { useState } from "react";
export const HomePage = () => {
    return (
        <Content>
            <WelcomeMessage> Welcome! </WelcomeMessage>
            <ExploreMessage>Explore Novi Pazar with us.</ExploreMessage>
        </Content>
    )
};