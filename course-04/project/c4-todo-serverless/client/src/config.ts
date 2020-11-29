// TODO: Once your application is deployed, copy an API id here so that the frontend could interact with it
const apiId = '46f1m8cxw1'
const region = 'us-east-2'
export const apiEndpoint = `https://${apiId}.execute-api.${region}.amazonaws.com/deve`

export const authConfig = {
  // TODO: Create an Auth0 application and copy values from it into this map
  domain: 'dev-2gg05ynn.us.auth0.com',            // Auth0 domain
  clientId: 'ocKOH8PjSJPeKkeAeimS9ihaiuJ4lmfp',          // Auth0 client id
  callbackUrl: 'http://localhost:3000/callback'
}
