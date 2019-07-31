import {Report} from './api';

export interface Parser {
    getReport(): Report;
}