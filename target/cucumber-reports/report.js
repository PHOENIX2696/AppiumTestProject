$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/features/HomePage.feature");
formatter.feature({
  "name": "HomePage feature of youtube",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "Navigation to youtube home page",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Launch the youtube application",
  "keyword": "Given "
});
formatter.match({
  "location": "HomePage.launchTheApplication()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify the youtube home page",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Android"
    }
  ]
});
formatter.step({
  "name": "Youtube home page displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "HomePage.youtubeHomePageDisplayed()"
});
formatter.result({
  "status": "passed"
});
});