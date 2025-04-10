export interface Movimiento {
    id?: number;
    cuentaId: number;
    tipo: string;
    saldoInicial: number;
    saldoDisponible: number;
    valor: number;
    fecha?: Date;
}
