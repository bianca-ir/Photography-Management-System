import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function ratingValidator(): ValidatorFn{
  return (control: AbstractControl) : ValidationErrors | null => {
    if (control.value !== undefined && (isNaN(control.value) || control.value < 0 || control.value > 10)) {
      return { 'ratingRange': true };
    }
    return null;
  }
}
