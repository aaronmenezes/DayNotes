import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NotepageComponent } from './notepage.component';

describe('NotepageComponent', () => {
  let component: NotepageComponent;
  let fixture: ComponentFixture<NotepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
