<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Questions</title>
    <link href="https://fonts.googleapis.com/css2?family=Work+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/questions.css">
</head>
<body>
<main>
    <h1 class="faq-heading">Часто задаваемые вопросы</h1>
    <section class="faq-container">
        <div class="faq-one">
            <h1 class="faq-page">Со скольки лет можно начать заниматься Самбо и Дзюдо?</h1>
            <div class="faq-body">
                <p>С 7-ми лет.</p>
            </div>
        </div>
        <hr class="hr-line">
        <div class="faq-two">
            <h1 class="faq-page">Существуют ограничения в возрасте? Мне за 40, не поздно начать?</h1>
            <div class="faq-body">
                <p>Ограничений нет, главное желание. </p>
            </div>
        </div>
        <hr class="hr-line">
        <div class="faq-three">
            <h1 class="faq-page">Можно ли заниматься девушкам Самбо и дзюдо?</h1>
            <div class="faq-body">
                <p>Да, можно. </p>
            </div>
        </div>
        <hr class="hr-line">
        <div class="faq-three">
            <h1 class="faq-page">Стоимость оплаты?</h1>
            <div class="faq-body">
                <p>2000 сомов в месяц. </p>
            </div>
        </div>
        <hr class="hr-line">
        <div class="faq-three">
            <h1 class="faq-page">Сколько тренировок в неделю?</h1>
            <div class="faq-body">
                <p>В зависимости от возраста, цели и уровня подготовленности </p>
            </div>
        </div>
        <hr class="hr-line">
    </section>
</main>
<script>
    let faq = document.getElementsByClassName("faq-page");
    let i;
    for (i = 0; i < faq.length; i++) {
        faq[i].addEventListener("click", function () {
            this.classList.toggle("active");
            let body = this.nextElementSibling;
            if (body.style.display === "block") {
                body.style.display = "none";
            } else {
                body.style.display = "block";
            }
        });
    }
</script>
</body>
</html>