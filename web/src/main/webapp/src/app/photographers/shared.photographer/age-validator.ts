import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export function ageValidator(): ValidatorFn{
  return (control: AbstractControl) : ValidationErrors | null => {
    if (control.value !== undefined && (isNaN(control.value) || control.value < 14 || control.value > 100)) {
      return { 'ratingRange': true };
    }
    return null;
  }
}
