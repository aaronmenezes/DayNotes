import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoteContentDialogComponent } from './note-content-dialog.component';

describe('NoteContentDialogComponent', () => {
  let component: NoteContentDialogComponent;
  let fixture: ComponentFixture<NoteContentDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoteContentDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteContentDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
