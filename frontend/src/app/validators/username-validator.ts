import { AbstractControl, AsyncValidatorFn, ValidationErrors } from "@angular/forms";
import { Observable, catchError, debounceTime, of, switchMap } from "rxjs";
import { ClienteService } from "../services/cliente.service";

export function usernameValidator(clienteService: ClienteService): AsyncValidatorFn{
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
        if (!control.value) {
            return of(null);
        }

        return clienteService.verificarUsername(control.value).pipe(
            debounceTime(500),
            switchMap((existe: boolean) => (existe ? of({ usernameRepetido: true }) : of(null))),
            catchError(() => of(null))
        );
    };
}