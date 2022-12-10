import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
  max-width: 28%;
  max-height: 600px;
  overflow: scroll;
`;

export const HorizontalLine = styled.hr`
  display: block;
  height: 1px;
  border: 0;
  border-top: 1px solid #ccc;
  margin: 0;
`;

export const Image = styled.img`
  border-radius: 5px;
`;
