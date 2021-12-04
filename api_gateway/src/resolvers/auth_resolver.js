const usersResolver = {
    Query: {
        userDetailList: (_, __,{ dataSources, userIdToken }) => {
            if (userId == userIdToken)
                return dataSources.authAPI.getUsers()
            else
                return null
        },
        userDetailById: (_, { userId }, { dataSources, userIdToken }) => {
            if (userId == userIdToken)
                return dataSources.authAPI.getUser(userId)
            else
                return null
        },
    },
    Mutation: {
        signUpUser: async(_, { userInput }, { dataSources }) => {
            const authInput = {
                username: userInput.username,
                name: userInput.name,
                last_name: userInput.last_name,
                email: userInput.email,
                phone: userInput.phone,
                address: userInput.address,
                password: userInput.password,
            }
            return dataSources.authAPI.createUser(authInput);
        },
        logIn: (_, { credentials }, { dataSources }) => 
            dataSources.authAPI.authRequest(credentials),

        refreshToken: (_, { refresh }, { dataSources }) => 
            dataSources.authAPI.refreshToken(refresh),
    }
};

module.exports = usersResolver;