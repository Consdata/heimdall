import {Report} from './api';

export interface Parser {
    parse(): void;
    getReport(): Report;
}