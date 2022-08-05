import {Projects} from './Projects';

export type ProjectDependency = {
  [key in Projects]: string;
}
