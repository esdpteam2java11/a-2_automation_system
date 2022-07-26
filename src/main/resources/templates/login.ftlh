<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet"
          crossorigin="anonymous"/>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <form class="form-signin" method="post" action="/login">
                            <div class="mb-md-5 mt-md-4 pb-5">

                                <h2 class="fw-bold mb-2 text-uppercase">Войти в систему</h2>
                                <p class="text-white-50 mb-5">Пожалуйста введите логин и пароль!</p>
                                <#if error!false >
                                    <p style="color:red">Неправильный логин или пароль!</p>
                                </#if>
                                <#if errorMessage?? >
                                    <p style="color:red">${errorMessage}</p>
                                </#if>
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="typeEmailX">Логин</label>
                                    <input type="text" name="username" id="typeEmailX"
                                           class="form-control form-control-lg"
                                           required autofocus/>

                                </div>
                                <div class="form-outline form-white mb-4">
                                    <label class="form-label" for="typePasswordX">Пароль</label>
                                    <input type="password" name="password" id="typePasswordX"
                                           class="form-control form-control-lg" required/>
                                </div>
                                <div>
                                    <input type="checkbox" class="form-check-input" id="remember-me"
                                           name="checkRememberMe">
                                    <label class="form-check-label" for="remember-me">Запомнить меня</label>
                                </div>

                                <button class="btn  btn-outline-success btn-lg px-5 mt-5" type="submit">Войти</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>