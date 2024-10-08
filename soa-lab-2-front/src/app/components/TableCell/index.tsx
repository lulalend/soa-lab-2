import styles from './styles.module.css';
import {PropsWithChildren} from "react";
import {Sort} from "@/app/components/Sort";
import {Properties} from "@/app/types/properties";

type TableCellProps = {
    cellKey?: Properties;
    className?: string;
    withSort?: boolean;
}

export const TableCell = ({ className, cellKey, withSort = false, children }: PropsWithChildren<TableCellProps>) => {
    return (
        <div className={`${styles.container} ${className}`}>
            <div className={styles.cell}>
                {children}
            </div>
            {(withSort === true && cellKey !== undefined) &&
                <Sort sortKey={cellKey}/>
            }
        </div>
    )
}