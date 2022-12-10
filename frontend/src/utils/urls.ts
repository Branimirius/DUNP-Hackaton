const path = "http://localhost:8081";

export const urls = {
  login: `${path}/authenticate`,
  register: `${path}/register`,
  createLocation: `${path}/geo-entity`,
  uploadImage: `${path}/geo-entity`,
  getLocations: `${path}/geo-entity`,
  addComment: `${path}/geo-entity-comment`,
  getLocationComments: `${path}`,
  getLocationImage: `${path}/local/geo-image`,
};
