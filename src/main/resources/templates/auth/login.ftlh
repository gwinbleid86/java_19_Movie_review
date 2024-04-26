<#import "../layout.ftlh" as main>
<@main.layout ; spring>

    <!-- Section: Design Block -->
    <section class=" text-center text-lg-start">
        <style>
            .rounded-t-5 {
                border-top-left-radius: 0.5rem;
                border-top-right-radius: 0.5rem;
            }

            @media (min-width: 992px) {
                .rounded-tr-lg-0 {
                    border-top-right-radius: 0;
                }

                .rounded-bl-lg-5 {
                    border-bottom-left-radius: 0.5rem;
                }
            }
        </style>
        <div class="card m-3">
            <div class="row g-0 d-flex align-items-center">
                <div class="col-lg-4 d-none d-lg-flex">
                    <img src="https://mdbootstrap.com/img/new/ecommerce/vertical/004.jpg" alt="Trendy Pants and Shoes"
                         class="w-100 rounded-t-5 rounded-tr-lg-0 rounded-bl-lg-5"/>
                </div>
                <div class="col-lg-8">
                    <div class="card-body py-5 px-md-5">

                        <form id="login-form" action="/auth/login" method="post">
                            <!-- Email input -->
                            <div data-mdb-input-init class="form-outline mb-4">
                                <input type="email" id="form2Example1" class="form-control" name="username"/>
                                <label class="form-label" for="form2Example1">Email address</label>
                            </div>

                            <!-- Password input -->
                            <div data-mdb-input-init class="form-outline mb-4">
                                <input type="password" id="form2Example2" class="form-control" name="password"/>
                                <label class="form-label" for="form2Example2">Password</label>
                            </div>

                            <!-- 2 column grid layout for inline styling -->
                            <#--                            <div class="row mb-4">-->
                            <#--                                <div class="col d-flex justify-content-center">-->
                            <#--                                    <!-- Checkbox &ndash;&gt;-->
                            <#--                                    <div class="form-check">-->
                            <#--                                        <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />-->
                            <#--                                        <label class="form-check-label" for="form2Example31"> Remember me </label>-->
                            <#--                                    </div>-->
                            <#--                                </div>-->

                            <#--                                <div class="col">-->
                            <#--                                    <!-- Simple link &ndash;&gt;-->
                            <#--                                    <a href="#!">Forgot password?</a>-->
                            <#--                                </div>-->
                            <#--                            </div>-->

                            <!-- Submit button -->
                            <button type="submit" data-mdb-button-init data-mdb-ripple-init
                                    class="btn btn-primary btn-block mb-4">Sign in
                            </button>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Section: Design Block -->

    <script>
        let form = document.getElementById('login-form')

        // form.addEventListener('submit', onLoginHandler)

        function onLoginHandler(event) {
            event.preventDefault()

            let target = event.target
            let data = new FormData(target)
            let user = Object.fromEntries(data)

            let headers = new Headers()
            headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))

            fetchAuthorized(headers)
            // let userJson = JSON.stringify(user)
            // localStorage.setItem('user', userJson)
            // window.location.href = 'http://localhost:8089/'

        }

        async function fetchAuthorized(auth) {
            try {
                await makeRequest('http://localhost:8089/api/auth/login', updateOptions({
                    method: 'post',
                    headers: auth
                }));
            } catch (e) {
                alert(e)
            }
        }

        // btoa()
        // atob()

        function makeHeaders() {
            let user = restoreUser()
            // console.log(user);

            let headers = new Headers()
            headers.set('Content-Type', 'application/json')
            if (user) {
                headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))
            }
            return headers
        }

        async function makeRequest(url, options) {
            let settings = options || requestSettings
            let response = await fetch(url, settings)

            if (response.ok) {
                return await response.json()
            } else {
                let err = new Error(response.status.toString())
                err.response = response
                console.log(err);
                throw err
            }
        }

        function updateOptions(options) {
            console.log(options);
            let update = {...options}
            update.mode = 'cors'
            update.headers = makeHeaders()
            return update
        }

        const requestSettings = {
            method: 'get',
            headers: makeHeaders()
        }

        function restoreUser() {
            return JSON.parse(localStorage.getItem('user'))
        }
    </script>
</@main.layout>