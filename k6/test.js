import http from 'k6/http';
import { check } from 'k6';


export const options = {
    scenarios: {
        warmup: {
            executor: "constant-vus",
            vus: 1,
            duration: '5s',
            startTime: '0s',
            gracefulStop: '1s',
            exec: 'runRecursive'
        },
        dynamic_scenario: {
            executor: "constant-vus",
            vus: 1000,
            duration: '10s',
            startTime: '6s',
            gracefulStop: '5s',
            exec: 'runDynamic'
        },
        recursive_scenario: {
            executor: "constant-vus",
            vus: 1000,
            duration: '10s',
            startTime: '17s',
            gracefulStop: '17s',
            exec: 'runRecursive'
        },
    },
};


export function runDynamic () {

    const url =
        'http://localhost:8080/find/dynamic/?val=100';


    const res = http.get(url);

    check(res, {
        'is status 200': (r) => r.status === 200,
        'is result correct': (r) => r.body.includes('3736710778780434371')
    })
}

export function runRecursive() {
    const url =
        'http://localhost:8080/find/recursive/?val=20';


    const res = http.get(url);

    check(res, {
        'is status 200': (r) => r.status === 200,
        'is result correct': (r) => r.body.includes('6765')
    })
}