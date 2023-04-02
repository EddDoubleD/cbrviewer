# Front
![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)

This project is working on [Angular CLI](https://github.com/angular/angular-cli) version 15.0.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

Attention, to avoid problems with CORS, has been added a proxy configuration file, see `front/src/proxy.conf.json`   

## Build

For production build, you need to change the url to `localhost:8080`

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

Move artifacts from `dist/` to `backend/src/main/resources/static` 

Build backend 

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
