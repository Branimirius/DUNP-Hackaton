import { Content, Image, SearchBarContainer, WelcomeMessage } from "./styles";
import { useState } from "react";
export const HomePage = () => {
    const [userinfo, setUserInfo] = useState<any>({
        languages: [],
    });

    const handleChange = (e: any) => {
        // Destructuring
        const { value, checked } = e.target;
        const { languages } = userinfo;


        // Case 1 : The user checks the box
        if (checked) {
            setUserInfo({
                languages: [...languages, value],
                response: [...languages, value],
            });
        }

        // Case 2  : The user unchecks the box
        else {
            setUserInfo({
                languages: languages.filter((e: any) => e !== value),
                response: languages.filter((e: any) => e !== value),
            });
        }
    };
    return (
        <>
            <SearchBarContainer>
                <div className="col-md-6">
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="Javascript"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            Javascript
                        </label>
                    </div>
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="Python"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            Python
                        </label>
                    </div>
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="Java"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            Java
                        </label>
                    </div>
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="PHP"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            PHP
                        </label>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="C#"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            C#
                        </label>
                    </div>
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="C++"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            C++
                        </label>
                    </div>
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="C"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            C
                        </label>
                    </div>
                    <div className="form-check m-3">
                        <input
                            className="form-check-input"
                            type="checkbox"
                            name="languages"
                            value="Typescript"
                            id="flexCheckDefault"
                            onChange={handleChange}
                        />
                        <label
                            className="form-check-label"
                            htmlFor="flexCheckDefault"
                        >
                            Typescript
                        </label>
                    </div>
                </div>
            </SearchBarContainer>

            <Image src={require('../../images/noviPazar.jpg')} alt="Novi Pazar" />
            <Content>
                <WelcomeMessage> Welcome to Novi Pazar! </WelcomeMessage>
            </Content>
        </>
    )
};