<#macro renderWith title="" scripts=[] styles=[]>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <title>Index</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700|Work+Sans:300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="/fonts/icomoon/style.css">

        <link rel="stylesheet" href="/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/magnific-popup.css">
        <link rel="stylesheet" href="/css/jquery-ui.css">
        <link rel="stylesheet" href="/css/owl.carousel.min.css">
        <link rel="stylesheet" href="/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="/css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="/css/animate.css">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/mediaelement@4.2.7/build/mediaelementplayer.min.css">


        <link rel="stylesheet" href="/fonts/flaticon/font/flaticon.css">

        <link rel="stylesheet" href="/css/aos.css">

        <link rel="stylesheet" href="/css/style.css">
        <link rel="stylesheet" href="/css/club.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="/css/news.css">
        <link rel="stylesheet" href="/css/questions.css">
        <link rel="stylesheet" href="/css/addUserForm.css">

    </head>
    <body>

    <div class="site-wrap">


        <div class="site-mobile-menu">
            <div class="site-mobile-menu-header">
                <div class="site-mobile-menu-close mt-3">
                    <span class="icon-close2 js-menu-toggle"></span>
                </div>
            </div>
            <div class="site-mobile-menu-body"></div>
        </div> <!-- .site-mobile-menu -->


        <div class="site-navbar-wrap js-site-navbar bg-white">

            <div class="container">
                <div class="site-navbar bg-light">
                    <div class="py-1">
                        <div class="row align-items-center">
                            <div class="col-1">
                                <a href="/"><img src="/images/logo.JPG" alt=""></a>
                            </div>
                            <hr>
                            <div class="col-13">
                                <nav class="site-navigation text-right" role="navigation">
                                    <div class="container">
                                        <div class="d-inline-block d-lg-none ml-md-0 mr-auto py-3"><a href="#"
                                                                                                      class="site-menu-toggle js-menu-toggle text-black"><span
                                                        class="icon-menu h3"></span></a></div>

                                        <ul class="site-menu js-clone-nav d-none d-lg-block">
                                            <li>
                                                <a href="/">Главная</a>
                                            </li>
                                            <li class="has-children">
                                                <a href="#">Наши тренеры</a>
                                                <ul class="dropdown arrow-top">
                                                    <li><a href="/trainer1">Арсен Камчибеков</a></li>
                                                    <li><a href="/trainer2">Актилек Беккулов</a></li>
                                                </ul>
                                            </li>

                                            <li class="has-children">
                                                <a href="#">Тренировочные процессы</a>
                                                <ul class="dropdown arrow-top">
                                                    <li><a href="/training">Тренировочные процессы</a></li>
                                                    <li><a href="/benefit">Что вы приобретаете</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="/news/all">Новости</a></li>
                                            <li><a href="/club">О клубе</a></li>
                                            <li><a href="/questions">Вопросы</a></li>
                                            <li><a href="/login">Войти</a></li>
                                        </ul>
                                    </div>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="site-wrap">

        </div>


        <#nested>


        <script src="/js/jquery-3.3.1.min.js"></script>
        <script src="/js/jquery-migrate-3.0.1.min.js"></script>
        <script src="/js/jquery-ui.js"></script>
        <script src="/js/popper.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
        <script src="/js/owl.carousel.min.js"></script>
        <script src="/js/jquery.stellar.min.js"></script>
        <script src="/js/jquery.countdown.min.js"></script>
        <script src="/js/jquery.magnific-popup.min.js"></script>
        <script src="/js/bootstrap-datepicker.min.js"></script>
        <script src="/js/aos.js"></script>

        <script src="/js/main.js"></script>

    </body>
    </html>
</#macro>