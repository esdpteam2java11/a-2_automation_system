<#import "partials/index_navbar.ftlh" as main />
<#import "partials/footer.ftlh" as footer />
<@main.renderWith >




    <!-- <div style="height: 113px;"></div> -->
    <div class="slide-one-item home-slider owl-carousel">

        <div class="site-blocks-cover inner-page" style="background-image: url(/images/hero_b1_1.jpg);" data-aos="fade"
             data-stellar-background-ratio="0.5">
            <!-- <div class="container"> -->
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7 text-center" data-aos="fade">
                    <h1>Будь лучшим!</h1>
                    <span class="caption d-block text-white">#постоянство</span>
                </div>
            </div>
            <!-- </div> -->
        </div>

        <div class="site-blocks-cover inner-page" style="background-image: url(/images/hero_bg_2.jpg);" data-aos="fade"
             data-stellar-background-ratio="0.5">
            <!-- <div class="container"> -->
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7 text-center" data-aos="fade">
                    <h1>Верь в себя!</h1>
                    <span class="caption d-block text-white">#постоянство</span>
                </div>
            </div>
            <!-- </div> -->
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                    <img class="card-img-top" src="/images/img_1.jpg" alt="Card image cap">

                </div>
                <div class="col-lg-5 ml-auto">
                    <h2 class="site-section-heading mb-3">Спортивный клуб "A-2"</h2>
                    <p>Спортивный клуб функционирует с августа 2020 года и специализируется по двум основным
                        направлениям: самбо и дзюдо.
                        По выростным критериям, группы разделены на 4 категории:
                        Детская группа: от 7-9 лет;
                        Младшая группа: от 10-13 лет;
                        Старшая группа: с 14 по 18 лет;
                        Взрослая группа: с 19 лет.
                    </p>
                    <p><a href="/club" class="btn btn-outline-primary py-2 px-4">Подробнее</a></p>
                </div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="site-section-heading text-center mb-3">Новости</h2>
                </div>
            </div>
            <#--            <div class="row">-->
            <#--                <div class="col-md-12 block-13 nav-direction-white">-->
            <#--                    <div class="nonloop-block-13 owl-carousel">-->
            <#--                        <div class="media-image">-->
            <#--                            <img src="/images/img_1.jpg" alt="Image" class="img-fluid">-->
            <#--                            <div class="media-image-body">-->
            <#--                                <h2>Новость 1</h2>-->
            <#--                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga, voluptate.</p>-->
            <#--                                <p><a href="#" class="btn btn-primary text-white px-4"><span-->
            <#--                                                class="caption">Подробнее</span></a></p>-->
            <#--                            </div>-->
            <#--                        </div>-->
            <#--                        <div class="media-image">-->
            <#--                            <img src="/images/img_2.jpg" alt="Image" class="img-fluid">-->
            <#--                            <div class="media-image-body">-->
            <#--                                <h2>Новость 2</h2>-->
            <#--                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga, voluptate.</p>-->
            <#--                                <p><a href="#" class="btn btn-primary text-white px-4"><span-->
            <#--                                                class="caption">Подробнее</span></a></p>-->
            <#--                            </div>-->
            <#--                        </div>-->
            <#--                        <div class="media-image">-->
            <#--                            <img src="/images/img_3.jpg" alt="Image" class="img-fluid">-->
            <#--                            <div class="media-image-body">-->
            <#--                                <h2>Новость 3</h2>-->
            <#--                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga, voluptate.</p>-->
            <#--                                <p><a href="#" class="btn btn-primary text-white px-4"><span-->
            <#--                                                class="caption">Подробнее</span></a></p>-->
            <#--                            </div>-->
            <#--                        </div>-->
            <#--                        <div class="media-image">-->
            <#--                            <img src="/images/img_4.jpg" alt="Image" class="img-fluid">-->
            <#--                            <div class="media-image-body">-->
            <#--                                <h2>Новость 4</h2>-->
            <#--                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga, voluptate.</p>-->
            <#--                                <p><a href="#" class="btn btn-primary text-white px-4"><span-->
            <#--                                                class="caption">Подробнее</span></a></p>-->
            <#--                            </div>-->
            <#--                        </div>-->
            <#--                        <div class="media-image">-->
            <#--                            <img src="/images/img_5.jpg" alt="Image" class="img-fluid">-->
            <#--                            <div class="media-image-body">-->
            <#--                                <h2>Новость 5</h2>-->
            <#--                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Fuga, voluptate.</p>-->
            <#--                                <p><a href="" class="btn btn-primary text-white px-4"><span-->
            <#--                                                class="caption">Подробнее</span></a></p>-->
            <#--                            </div>-->
            <#--                        </div>-->

            <#--                        </div>-->
            <#--                    </div>-->
            <#--                </div>-->
<#--            <div class="row">-->
<#--                <div class="nonloop-block-13 owl-carousel">-->
<#--                    <#list news as new>-->
<#--                        <div class="media-image">-->
<#--                            <a href="/news/${new.id}"><img src="/upload/${new.image}" alt="Image" class="img-fluid"></a>-->
<#--                            <div class="media-image-body">-->
<#--                                <a href="/news/${new.id}"><h2>${new.title}</h2></a>-->
<#--                                <p>${new.description}</p>-->
<#--                                <p>${new.date}</p>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                    </#list>-->
<#--                </div>-->
<#--            </div>-->
        </div>
    </div>
    </div>


    <div class="site-section block-14 bg-light nav-direction-white">

        <div class="container">

            <div class="row mb-5">
                <div class="col-md-12">
                    <h2 class="site-section-heading-red text-center">Тренеры</h2>
                </div>
            </div>

            <div class="nonloop-block-14 owl-carousel">

                <div class="p-5">
                    <div class="d-flex block-testimony">
                        <div class="person mr-3">
                            <img src="/images/person_1.jpg" alt="Image" class="img-fluid rounded-circle">
                        </div>
                        <div>
                            <h2 class="h5">Арсен Камчибеков</h2>
                            <blockquote>&ldquo;Арсен Камчибеков, является мастером спорта КР,
                                имеет специализированное образование в области физической культуры и спорта,
                                также является преподавателем и заведующим кафедрой СБЕС КГАФКиС.&rdquo;
                            </blockquote>
                            <p><a href="/trainer1" class="btn btn-outline-primary py-2 px-4">Подробнее</a></p>
                        </div>
                    </div>
                </div>
                <div class="p-5">
                    <div class="d-flex block-testimony">
                        <div class="person mr-3">
                            <img src="/images/person_2.jpg" alt="Image" class="img-fluid rounded-circle">
                        </div>
                        <div>
                            <h2 class="h5">Актилек Беккулов</h2>
                            <blockquote>&ldquo;Является тренером спортивного клуба «А-2» с 2020 года.
                                Имеет специализированное образование в области физической культуры и спорта&rdquo;
                            </blockquote>
                            <p><a href="/trainer2" class="btn btn-outline-primary py-2 px-4">Подробнее</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <@footer.renderWith>

    </@footer.renderWith>
    </div>
</@main.renderWith>
