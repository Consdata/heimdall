import {Injectable} from '@angular/core';
import {BehaviorSubject} from 'rxjs';

export enum ViewState {
  GRID, CARDS
}

@Injectable({
  providedIn: 'root'
})
export class ViewStateService {

  private messageSource = new BehaviorSubject(ViewState[localStorage.getItem('viewMode')]);
  currentState = this.messageSource.asObservable();

  constructor() { }

  changeStateToGridView(){
    this.messageSource.next(ViewState.GRID)
    localStorage.setItem('viewMode', 'GRID');
  }

  changeStateToCardsView(){
    this.messageSource.next(ViewState.CARDS)
    localStorage.setItem('viewMode', 'CARDS');
  }
}
