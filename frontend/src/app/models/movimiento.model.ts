export interface Cliente {
    id: number;
    nombre: string;
}

export interface Cuenta {
    id?: number;
    numeroCuenta: string;
    cliente?: Cliente;
}

export interface Movimiento {
    id?: number;
    cuentaId: number;
    tipoMovimiento: string;
    saldoInicial: number;
    saldoDisponible: number;
    valor: number;
    fecha?: Date;
    cuenta?: Cuenta;
}
