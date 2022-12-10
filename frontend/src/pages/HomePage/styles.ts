import styled from "styled-components";

export const Image = styled.img`
  z-index: -1;
  position: absolute;

  width: 100%;

  background-repeat: no-repeat;
  background-attachment: fixed;

  opacity: 0.6;
`;

export const Content = styled.div`
  text-align: center;
`;

export const WelcomeMessage = styled.p`
  margin-top: 200px;
  color: #273be2;
  font-size: 100px;
`;

export const SearchBarContainer = styled.div`
  display: flex;
  gap: 15px;
  margin: 10px;
`;
