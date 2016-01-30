require.config({
    waitSeconds: 120,
    paths: {
        /* Core Libs */
        'jquery': 'bower_components/jquery/jquery',
        'angular': 'bower_components/angular/angular',
        'angular-route': 'bower_components/angular-route/angular-route',
        'app': 'scripts/app'
    },

    shim: {
        'angular-route':{
            deps: ['angular'],
            exports: 'angular-route'
        }
    }
});

define(['app'], function(app){
})

